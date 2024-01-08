package com.color.colorapp.dto;

public class BetPlacementDTO {
    private String username;
    private Double betAmount;
    private String betType; // "color" or "number"
    private String betValue; // e.g., "red" or "7"

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Double betAmount) {
        this.betAmount = betAmount;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getBetValue() {
        return betValue;
    }

    public void setBetValue(String betValue) {
        this.betValue = betValue;
    }
}
