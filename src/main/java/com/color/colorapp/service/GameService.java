package com.color.colorapp.service;

import com.color.colorapp.dto.RoundResultDTO;
import com.color.colorapp.entity.Round;
import com.color.colorapp.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private FinanceService financeService;
    @Autowired
    private SimpMessagingTemplate template;

    private final Random random = new Random();

    public void phase2() {
        // Close the current round and process results
        Round currentRound = getCurrentRound();
        if (currentRound != null && "ongoing".equals(currentRound.getStatus())) {
            currentRound.setStatus("betting closed");

            // Generate a random winning number
            int winningNumber = random.nextInt(10); // Random number between 0 and 9
            if (currentRound.getResult() == null) {
                currentRound.setResult(new Round.Result(null, null));
            }
            currentRound.getResult().setWinningNumber(winningNumber);

            roundRepository.save(currentRound);

            RoundResultDTO resultDto = new RoundResultDTO();
            resultDto.setRoundId(currentRound.getRoundId());
            resultDto.setWinningNumber(winningNumber);
            financeService.declareRoundResult(resultDto);
        }
    }

    public void startNewBettingRound() {
        // Create a new round
        String newRoundId = generateNewRoundId();
        Round newRound = new Round();
        newRound.setRoundId(newRoundId);
        newRound.setStatus("ongoing");
        newRound.setTimestamp(Instant.now());
        newRound.setBets(new ArrayList<>());
        roundRepository.save(newRound);
    }

    private Round getCurrentRound() {
        String todayRoundIdPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return roundRepository.findTopByRoundIdStartingWithOrderByRoundIdDesc(todayRoundIdPrefix);
    }


    private String generateNewRoundId() {
        String todayRoundIdPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Round lastRound = roundRepository.findTopByRoundIdStartingWithOrderByRoundIdDesc(todayRoundIdPrefix);

        int nextRoundNumber = 1;
        if (lastRound != null && lastRound.getRoundId().startsWith(todayRoundIdPrefix)) {
            try {
                String lastRoundNumberStr = lastRound.getRoundId().substring(8);
                int lastRoundNumber = Integer.parseInt(lastRoundNumberStr);
                nextRoundNumber = lastRoundNumber + 1;
            } catch (NumberFormatException e) {
                System.out.println("an unexpected format in the round ID");
            }
        }
        return todayRoundIdPrefix + String.format("%02d", nextRoundNumber);
    }



    // release 4 : Webscockets and integrating with angular

//    public void broadcastTimer() {
//        // Logic to calculate the remaining time for the current round
//        String remainingTime = "03:00"; // e.g., "02:14"
//        template.convertAndSend("/topic/timer", remainingTime);
//    }
}
