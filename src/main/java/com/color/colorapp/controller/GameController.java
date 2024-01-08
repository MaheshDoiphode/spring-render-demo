package com.color.colorapp.controller;

import com.color.colorapp.service.FinanceService;
import com.color.colorapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;


    @Scheduled(fixedDelay = 150000) // 2.5 minutes
    public void manageGameClosure() {
        gameService.phase2(); // Close betting, generate random number, update balances, etc.
    }

    @Scheduled(fixedDelay = 180000) // 3 minutes
    public void startNewRound() {
        gameService.startNewBettingRound(); // Create and open a new round for betting
    }


}
