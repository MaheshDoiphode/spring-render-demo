package com.color.colorapp.controller;

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
    public ResponseEntity<List<GameResultDto>> getRounds() {
        List<Round> rounds = roundService.getAllRounds();
        List<GameResultDto> roundDtos = rounds.stream()
                .map(this::convertToGameResultDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roundDtos);
    }



    private GameResultDto convertToGameResultDto(Round round) {
        GameResultDto dto = new GameResultDto();
        dto.setPeriod(round.getRoundId());
        dto.setPrice(round.getTotalBettingAmount());
        if (round.getResult() != null) {
            dto.setNumber(round.getResult().getWinningNumber());
            // Additional logic to set 'result'
        }
        return dto;
    }





















    @Scheduled(fixedRate = 150000)
    public void manageGameClosure() {
        System.out.println("Closing the bets.");
        gameService.phase2(); // Close betting, generate random number, update balances, etc.
    }

    @Scheduled(fixedRate = 180000)
    public void startNewRound() {
        System.out.println("New Round starting");
        try{
            gameService.startNewBettingRound(); // Create and open a new round for betting
        }catch (Exception e){
            System.out.println(e.getMessage() + " Error occurred in startNewRound");
        }
    }


}
