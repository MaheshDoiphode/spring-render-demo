package com.color.colorapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.color.colorapp.service.TimerService;

@Controller
public class WebSocketController {

    @Autowired
    private TimerService timerService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/subscribeTimer")
    public void subscribeTimer() {
        // Broadcast the current timer status to the newly subscribed client
        long remainingTime = timerService.getRemainingTime();
        messagingTemplate.convertAndSend("/topic/timer", remainingTime);
    }
}
