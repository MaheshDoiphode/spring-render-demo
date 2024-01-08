package com.color.colorapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "game_details")
public class GameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private double platform_balance;

    @Column
    private int last_round_no;

    @Column
    private Date last_round_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlatform_balance() {
        return platform_balance;
    }

    public void setPlatform_balance(double platform_balance) {
        this.platform_balance = platform_balance;
    }

    public int getLast_round_no() {
        return last_round_no;
    }

    public void setLast_round_no(int last_round_no) {
        this.last_round_no = last_round_no;
    }

    public Date getLast_round_date() {
        return last_round_date;
    }

    public void setLast_round_date(Date last_round_date) {
        this.last_round_date = last_round_date;
    }
}