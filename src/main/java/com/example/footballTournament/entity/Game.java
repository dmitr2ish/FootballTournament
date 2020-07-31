package com.example.footballTournament.entity;

import javax.persistence.*;

@Entity
@Table(name = "ft_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long firstTeamId;
    private Long secondTeamId;
    private Long winnerId;

    public Game() {
    }

    public Game(Long firstTeamId, Long secondTeamId, Long winnerId) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.winnerId = winnerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Long firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Long getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Long secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
