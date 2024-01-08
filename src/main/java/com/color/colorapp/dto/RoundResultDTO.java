package com.color.colorapp.dto;

public class RoundResultDTO {
    private String roundId;
    private int winningNumber;

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(int winningNumber) {
        this.winningNumber = winningNumber;
    }
}
