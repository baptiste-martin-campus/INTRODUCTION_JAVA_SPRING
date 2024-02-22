package fr.le_campus_numerique.intro_java_spring.interfaces;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

public interface GameCatalog {

    Collection<String> getGameIdentifiers();
    GameFactory getFactory(String gameType);

}
