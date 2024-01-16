package com.color.colorapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerService {
    private static final long ROUND_DURATION = 180000; // 3 minutes in milliseconds
    private long roundEndTime;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void resetTimer() {
        roundEndTime = System.currentTimeMillis() + ROUND_DURATION;
        broadcastTime();
    }

    @Scheduled(fixedRate = 1000) // Update every second
    public void broadcastTime() {
        long remainingTime = roundEndTime - System.currentTimeMillis();
        if (remainingTime < 0) {
            remainingTime = 0;
        }
        messagingTemplate.convertAndSend("/topic/timer", remainingTime);
    }

    public long getRemainingTime() {
        long remainingTime = roundEndTime - System.currentTimeMillis();
        return remainingTime < 0 ? 0 : remainingTime;
    }
}
