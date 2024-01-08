package com.color.colorapp.controller;

import com.color.colorapp.dto.BetPlacementDTO;
import com.color.colorapp.dto.RechargeDTO;
import com.color.colorapp.dto.RoundResultDTO;
import com.color.colorapp.dto.WithdrawalDTO;
import com.color.colorapp.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/bet")
    public ResponseEntity<?> placeBet(@RequestBody BetPlacementDTO betDto) {
        try {
            financeService.placeBet(betDto);
            return ResponseEntity.ok("Bet placed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error placing bet");
        }
    }

    @PostMapping("/result")
    public ResponseEntity<?> declareRoundResult(@RequestBody RoundResultDTO resultDto) {
        try {
            financeService.declareRoundResult(resultDto);
            return ResponseEntity.ok("Round result declared successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error declaring round result");
        }
    }



    //release 3 :
    @PostMapping("/recharge")
    public ResponseEntity<?> rechargeAccount(@RequestBody RechargeDTO rechargeDto) {
        try {
            financeService.rechargeAccount(rechargeDto);
            return ResponseEntity.ok("Account recharged successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawAmount(@RequestBody WithdrawalDTO withdrawalDto) {
        try {
            financeService.withdrawAmount(withdrawalDto);
            return ResponseEntity.ok("Amount withdrawn successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}