package com.example.footballTournament.entity;

import javax.persistence.*;

@Entity
@Table(name = "ft_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstTeam;
    private String secondTeam;

    private String winner;

    public Game() {
    }

    public Game(String firstTeam, String secondTeam, String winner) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
