package fr.le_campus_numerique.intro_java_spring.dto;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Collection;
import java.util.Map;

public record GameDTO (String gameId, Map<CellPosition, Token> board , GameStatus status, Collection<Token> tokens){
}
