package com.color.colorapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

@Document(collection = "rounds")
public class Round {
    @Id
    private String id;

    private String roundId;
    private List<Bet> bets;
    private Result result;
    private Instant timestamp;
    private String status;

    public static class Bet {
        private Long userId;
        private String betType; // "color" or "number"
        private String betValue; // Color name or number
        private Double betAmount;
        private Double payout;
        private String result; // "win", "lose", "pending"

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
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

        public Double getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(Double betAmount) {
            this.betAmount = betAmount;
        }

        public Double getPayout() {
            return payout;
        }

        public void setPayout(Double payout) {
            this.payout = payout;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public static class Result {
        private Integer winningNumber;
        private String winningColor;
        public Result(Integer winningNumber, String winningColor) {
            this.winningNumber = winningNumber;
            this.winningColor = winningColor;
        }

        public Integer getWinningNumber() {
            return winningNumber;
        }

        public void setWinningNumber(Integer winningNumber) {
            this.winningNumber = winningNumber;
        }

        public String getWinningColor() {
            return winningColor;
        }

        public void setWinningColor(String winningColor) {
            this.winningColor = winningColor;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Double totalBettingAmount;

    public Double getTotalBettingAmount() {
        return totalBettingAmount;
    }

    public void setTotalBettingAmount(Double totalBettingAmount) {
        this.totalBettingAmount = totalBettingAmount;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id='" + id + '\'' +
                ", roundId='" + roundId + '\'' +
                ", bets=" + bets +
                ", result=" + result +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", totalBettingAmount=" + totalBettingAmount +
                '}';
    }
}
