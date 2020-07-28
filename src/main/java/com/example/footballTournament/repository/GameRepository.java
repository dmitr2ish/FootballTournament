package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Game;

import java.util.List;

public interface GameRepository {
    List<Game> getAllGames();
    void deleteAllGames();
    Game getByGameId(Long id);
    Game saveGame(Game game);
    void updateGame(Game game);
}
