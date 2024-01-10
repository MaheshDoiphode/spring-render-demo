package com.color.colorapp.dto;

public class RoundDto {
    private String roundId;
    private Double totalBet;
    private Integer winningNumber;
    private String resultColor;

    // Getters and setters
    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public Double getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(Double totalBet) {
        this.totalBet = totalBet;
    }

    public Integer getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(Integer winningNumber) {
        this.winningNumber = winningNumber;
    }

    public String getResultColor() {
        return resultColor;
    }

    public void setResultColor(String resultColor) {
        this.resultColor = resultColor;
    }
}
