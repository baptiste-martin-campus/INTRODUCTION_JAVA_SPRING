package fr.le_campus_numerique.intro_java_spring.interfaces;

import fr.le_campus_numerique.intro_java_spring.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.Collection;
import java.util.Locale;

public interface GamePlugin {

    String getName(Locale locale);

    int getPlayerCount();

}
