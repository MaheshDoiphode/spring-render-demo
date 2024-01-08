package com.color.colorapp.service;

import com.color.colorapp.dto.BetPlacementDTO;
import com.color.colorapp.dto.RoundResultDTO;
import com.color.colorapp.entity.*;
import com.color.colorapp.repository.GameDetailsRepository;
import com.color.colorapp.repository.RoundRepository;
import com.color.colorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class FinanceService {

    private final RoundRepository roundRepository;
    private final UserRepository userRepository;
    private final GameDetailsRepository gameDetailsRepository;
    @Autowired
    public FinanceService(RoundRepository roundRepository, UserRepository userRepository, GameDetailsRepository gameDetailsRepository) {
        this.roundRepository = roundRepository;
        this.userRepository = userRepository;
        this.gameDetailsRepository = gameDetailsRepository;
    }

    public void closeBetting() {
        Round currentRound = getCurrentRound();
        if (currentRound != null && "ongoing".equals(currentRound.getStatus())) {
            currentRound.setStatus("betting closed");
            roundRepository.save(currentRound);
        }
    }

    @Transactional
    public void placeBet(BetPlacementDTO betDto) {
        User user = userRepository.findByUsername(betDto.getUsername());

        if (user == null || user.getWalletBalance() < betDto.getBetAmount()) {
            throw new RuntimeException("Invalid user or insufficient balance");
        }

        Round currentRound = getCurrentRound();
        if (currentRound == null || !"ongoing".equals(currentRound.getStatus())) {
            throw new RuntimeException("Betting is closed for the current round");
        }



        user.setWalletBalance(user.getWalletBalance() - betDto.getBetAmount());
        userRepository.save(user);


        Round.Bet bet = createBetFromDTO(betDto, user.getUserId());
        currentRound.getBets().add(bet);
        currentRound.setTotalBettingAmount(currentRound.getTotalBettingAmount() + betDto.getBetAmount());
        roundRepository.save(currentRound);
        updatePlatformBalance(2.0);
    }



    @Transactional
    public void declareRoundResult(RoundResultDTO resultDto) {
        Round round = roundRepository.findByRoundId(resultDto.getRoundId());
        if (round == null) {
            throw new RuntimeException("Round not found");
        }
        String winningColor = getWinningColor(resultDto.getWinningNumber());
        round.setResult(new Round.Result(resultDto.getWinningNumber(), winningColor));
        round.setStatus("completed");
        roundRepository.save(round);

        round.getBets().forEach(bet -> {
            if (isBetWinning(bet, resultDto.getWinningNumber())) {
                double payout = calculatePayout(bet);
                updateUserBalance(bet.getUserId(), payout);
            }
        });

        double totalPayouts = calculateTotalPayouts(round);
        double totalBets = round.getBets().stream().mapToDouble(Round.Bet::getBetAmount).sum();
        double serviceFees = round.getBets().size() * 2; // Assuming a fixed service fee of 2 units
        double earningsFromRound = totalBets - totalPayouts + serviceFees;

        updatePlatformBalance(earningsFromRound);
    }

    private boolean isBetWinning(Round.Bet bet, int winningNumber) {
        if ("number".equals(bet.getBetType())) {
            return bet.getBetValue().equals(String.valueOf(winningNumber));
        } else {
            return isWinningColor(bet.getBetValue(), winningNumber);
        }
    }

    private boolean isWinningColor(String color, int number) {
        switch (color) {
            case "green":
                return number == 1 || number == 3 || number == 7 || number == 9;
            case "red":
                return number == 2 || number == 4 || number == 6 || number == 8 || number == 0;
            case "violet":
                return number == 0 || number == 5;
            default:
                return false;
        }
    }

    private double calculatePayout(Round.Bet bet) {
        double contractAmount = bet.getBetAmount() - 2; // Deducting service fee
        if ("number".equals(bet.getBetType())) {
            return contractAmount * 9; // SELECT NUMBER
        } else if ("green".equals(bet.getBetType()) || "red".equals(bet.getBetType())) {
            return contractAmount * 2; // JOIN GREEN or RED
        } else if ("violet".equals(bet.getBetType())) {
            return contractAmount * 4.5; // JOIN VIOLET
        }
        return 0;
    }

    private void updateUserBalance(Long userId, double payout) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setWalletBalance(user.getWalletBalance() + payout);
            userRepository.save(user);
        }
    }


    private void updatePlatformBalance(double earningsFromRound) {
        GameDetails gameDetails = gameDetailsRepository.findTopByOrderByIdDesc();
        if (gameDetails == null) {
            gameDetails = new GameDetails();
            gameDetails.setPlatform_balance(0);
            gameDetails.setLast_round_no(0);
            gameDetails.setLast_round_date(new Date());
        }
        gameDetails.setPlatform_balance(gameDetails.getPlatform_balance() + earningsFromRound);
        gameDetailsRepository.save(gameDetails);
    }


    private double calculateTotalPayouts(Round round) {
        return round.getBets().stream()
                .filter(bet -> isBetWinning(bet, round.getResult().getWinningNumber()))
                .mapToDouble(bet -> calculatePayout(bet))
                .sum();
    }












    private Round getCurrentRound() {
        String todayRoundIdPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return roundRepository.findTopByRoundIdStartingWithOrderByRoundIdDesc(todayRoundIdPrefix);
    }

    private Round.Bet createBetFromDTO(BetPlacementDTO betDto, Long userId) {
        Round.Bet bet = new Round.Bet();
        bet.setUserId(userId);
        bet.setBetType(betDto.getBetType());
        bet.setBetValue(betDto.getBetValue());
        bet.setBetAmount(betDto.getBetAmount());
        bet.setPayout(0.0); // Initial payout is 0, to be updated after round result
        bet.setResult("pending"); // Initial result status
        return bet;
    }




    private String getWinningColor(int winningNumber) {
        switch (winningNumber) {
            case 0: return "purple";
            case 1:
            case 3:
            case 7:
            case 9: return "green";
            case 2:
            case 4:
            case 6:
            case 8: return "red";
            case 5: return "violet";
            default: return "";
        }
    }
}
