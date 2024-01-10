package com.color.colorapp.service;

import com.color.colorapp.entity.Round;
import com.color.colorapp.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundService {


    @Autowired
    private RoundRepository roundRepository;

    @Scheduled(fixedDelay = 180000) // 3 minutes
    public void processRound() {
        // Logic for processing the round
    }

    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }
}
