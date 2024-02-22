package fr.le_campus_numerique.intro_java_spring.services;

import fr.le_campus_numerique.intro_java_spring.dto.GameCreationParams;
import fr.le_campus_numerique.intro_java_spring.dto.TokenDTO;
import fr.le_campus_numerique.intro_java_spring.interfaces.GameCatalog;
import fr.le_campus_numerique.intro_java_spring.interfaces.GamePlugin;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.intro_java_spring.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameCatalog gameCatalog;

    @Autowired
    private List<GamePlugin> gamePlugins;

    private Map<String, Game> gameMap = new HashMap<>();


    @Override
    public Game createGame(String typeOfGame, int boardSize, int playerCount) {
        GameFactory factory = gameCatalog.getFactory(typeOfGame);
        Game game = factory.createGame(playerCount, boardSize);
        gameMap.put(game.getId().toString(),game);
        return game;
    }

    @Override
    public Collection<Game> findAllGames() {
        return gameMap.values();
    }

    @Override
    public Game findOneById(String id) {
        Game entry = null;
        for (int i = 0; i < gameMap.size(); i++) {
            if (gameMap.containsKey(id)){
                entry = gameMap.get(id);
            }
        }
        return entry;
    }

    @Override
    public Game update(String id, GameCreationParams params) {
        Game game = findOneById(id);
        return game;
    }

    @Override
    public void delete(String id) {
        gameMap.remove(id);
    }

    @Override
    public Game move(String gameId, TokenDTO tokenDTO) {
        Game game = findOneById(gameId);
        Collection<Token> tokens = game.getRemainingTokens();
        for (Token token : tokens) {
            if (token.canMove() && tokenDTO.name().equals(token.getName()) ){
                try {
                    token.moveTo(tokenDTO.position());
                    return game;
                } catch (InvalidPositionException error){
                    System.err.println(error);
                }
            }
        }
        return null;
    }

    public void setGamePlugins(List<GamePlugin> gamePlugins){
        this.gamePlugins = gamePlugins;
    }

    public List<GamePlugin> getGamePlugins(){
        return this.gamePlugins;
    }

}
