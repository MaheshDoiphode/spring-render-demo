package com.color.colorapp.dto;

public class WithdrawalDTO {
    private String username;
    private String password;
    private Double amount;
    private String withdrawalMethod; // "bank" or "upi"

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWithdrawalMethod() {
        return withdrawalMethod;
    }

    public void setWithdrawalMethod(String withdrawalMethod) {
        this.withdrawalMethod = withdrawalMethod;
    }
}
