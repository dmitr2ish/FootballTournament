package com.example.footballTournament.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ft_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<Team> teams;

    public Group() {
    }

    public Group(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public String listTeamsToString() {
        StringBuilder listTeams = new StringBuilder();
        for (int i = 0; i < teams.size(); i++) {
            if (i == teams.size() - 1) {
                listTeams.append(teams.get(i).getName());
                break;
            }
            listTeams.append(teams.get(i).getName());
            listTeams.append(", ");
        }
        return listTeams.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
