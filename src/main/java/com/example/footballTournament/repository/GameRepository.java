package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Game;

import java.util.List;

public interface GameRepository {
    List<Game> getAllGames();
    List<Game> getAllGamesByTeamName(String teamName);
    void deleteAllGames();
    Game getByGameId(Long id);
    Game saveGame(Game game);
    void updateGame(Game game);
    Integer getNumOfWins(String teamName);
    Integer getNumOfLose(String teamName);
    Integer getNumOfDraw(String teamName);
}
