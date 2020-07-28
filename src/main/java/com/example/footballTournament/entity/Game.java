package com.example.footballTournament.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfVictroies;
    private Integer numberOfDraws;
    private Integer numberOfLosses;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer points;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "gameList")
    private List<Team> teamList;

    public Game(Integer numberOfVictroies, Integer numberOfDraws, Integer numberOfLosses, Integer scoredGoals, Integer concededGoals, Integer points, List<Team> teamList) {
        this.numberOfVictroies = numberOfVictroies;
        this.numberOfDraws = numberOfDraws;
        this.numberOfLosses = numberOfLosses;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.points = points;
        this.teamList = teamList;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfVictroies() {
        return numberOfVictroies;
    }

    public void setNumberOfVictroies(Integer numberOfVictroies) {
        this.numberOfVictroies = numberOfVictroies;
    }

    public Integer getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(Integer numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public Integer getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses(Integer numberOfLosses) {
        this.numberOfLosses = numberOfLosses;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Integer getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(Integer concededGoals) {
        this.concededGoals = concededGoals;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
