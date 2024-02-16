package org.campusnumerique.introjavaspring;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GameCatalogDummyImpl implements GameCatalog{

    TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
    Collection<String> gameCollection = new ArrayList<>();

    GameCatalogDummyImpl(){
        gameCollection.add(ticTacToeGameFactory.getGameId());
    }
    @Override
    public Collection<String> getGameIdentifiers() {
        return gameCollection;
    }
}
