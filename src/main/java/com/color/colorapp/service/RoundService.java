package com.color.colorapp.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RoundService {

    @Scheduled(fixedDelay = 180000) // 3 minutes
    public void processRound() {
        // Logic for processing the round
    }
}
