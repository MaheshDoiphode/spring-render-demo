package com.color.colorapp.controller;

import com.color.colorapp.service.FinanceService;
import com.color.colorapp.service.GameService;
import com.color.colorapp.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import com.color.colorapp.dto.*;
import com.color.colorapp.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private RoundService roundService;

    @GetMapping("/rounds")
    public ResponseEntity<List<RoundDto>> getRounds() {
        List<Round> rounds = roundService.getAllRounds();
        List<RoundDto> roundDtos = rounds.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roundDtos);
    }

    private RoundDto convertToDto(Round round) {
        RoundDto dto = new RoundDto();
        dto.setRoundId(round.getRoundId());
        dto.setTotalBet(round.getTotalBettingAmount());
        if (round.getResult() != null) {
            dto.setWinningNumber(round.getResult().getWinningNumber());
            dto.setResultColor(round.getResult().getWinningColor());
        }
        return dto;
    }





















    @Scheduled(fixedDelay = 150000) // 2.5 minutes
    public void manageGameClosure() {
        gameService.phase2(); // Close betting, generate random number, update balances, etc.
    }

    @Scheduled(fixedDelay = 180000) // 3 minutes
    public void startNewRound() {
        gameService.startNewBettingRound(); // Create and open a new round for betting
    }


}
