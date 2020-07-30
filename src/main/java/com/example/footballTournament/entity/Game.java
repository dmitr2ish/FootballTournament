package com.example.footballTournament.entity;

import javax.persistence.*;

@Entity
@Table(name = "ft_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commandName;
    private Integer points;

    public Game() {
    }


    public Game(String commandName, Integer points) {
        this.commandName = commandName;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
