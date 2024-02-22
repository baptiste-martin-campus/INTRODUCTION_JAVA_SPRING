package fr.le_campus_numerique.intro_java_spring.services;

import fr.le_campus_numerique.intro_java_spring.interfaces.GameCatalog;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    private final Map<String, GameFactory> factories = new HashMap<>();

    public GameCatalogDummyImpl(){
        GameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
        GameFactory connectFourGameFactory = new ConnectFourGameFactory();
        GameFactory taquinGameFactory = new TaquinGameFactory();

        factories.put(ticTacToeGameFactory.getGameFactoryId(), ticTacToeGameFactory);
        factories.put(connectFourGameFactory.getGameFactoryId(), connectFourGameFactory);
        factories.put(taquinGameFactory.getGameFactoryId(), taquinGameFactory);
    }
    @Override
    public Set<String> getGameIdentifiers() {
        return factories.keySet();
    }

    public GameFactory getFactory(String gameType){
        return factories.get(gameType);
    }
}
