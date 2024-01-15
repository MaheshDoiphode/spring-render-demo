package com.color.colorapp.dto;

public class UpiDTO {
    private String username;
    private String password;
    private String upiId;

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

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public String toString() {
        return "UpiDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", upiId='" + upiId + '\'' +
                '}';
    }
}
