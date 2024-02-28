package fr.le_campus_numerique.intro_java_spring.interfaces;

import fr.le_campus_numerique.intro_java_spring.dto.TokenDTO;
import fr.le_campus_numerique.intro_java_spring.entities.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.Collection;

public interface GameService {

    Game createGame(String typeOfGame, int boardSize, int playerCount);
    Collection<Game> findAllGames();
    Game findOneById(String id);
    Game update(String id, GameCreationParams params);
    void delete(String id);
    Game move(String gameId, TokenDTO tokenDTO) throws InvalidPositionException;
}
