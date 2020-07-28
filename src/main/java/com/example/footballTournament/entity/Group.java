package com.example.footballTournament.entity;

import javax.persistence.*;
import java.util.List;
//Команды объединены в группы (4 команды в группе)
// Групп 8 штук (обычно именуют от A до H). Пример: "Group G"
@Entity
@Table(name = "groups")
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
