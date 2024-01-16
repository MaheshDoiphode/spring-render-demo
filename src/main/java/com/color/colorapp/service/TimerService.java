package com.color.colorapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class TimerService extends TextWebSocketHandler {

    private static final long ROUND_DURATION = 180000; // 3 minutes in milliseconds
    private long roundEndTime;
    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public TimerService() {
        resetTimer();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessions.put(session.getId(), session);
        broadcastTime(session, getRemainingTime());
    }

    @Scheduled(fixedRate = 1000) // Update every second
    public void broadcastTime() {
        long remainingTime = getRemainingTime();
        sessions.values().forEach(session -> broadcastTime(session, remainingTime));
    }

    private void broadcastTime(WebSocketSession session, long remainingTime) {
        if (session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(Long.toString(remainingTime)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetTimer() {
        roundEndTime = System.currentTimeMillis() + ROUND_DURATION;
    }

    public long getRemainingTime() {
        long remainingTime = roundEndTime - System.currentTimeMillis();
        return Math.max(remainingTime, 0);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }
}
