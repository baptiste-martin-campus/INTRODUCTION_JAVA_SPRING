package fr.le_campus_numerique.intro_java_spring.dto;

import fr.le_campus_numerique.square_games.engine.CellPosition;

import java.util.Set;

public record TokenDTO(String name, CellPosition position) {
}
