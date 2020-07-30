package com.example.footballTournament.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ft_teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "ft_game_team",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Game> gameList;

    public Team(String name, List<Game> gameList) {
        this.name = name;
        this.gameList = gameList;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGameList() {
        return gameList;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
