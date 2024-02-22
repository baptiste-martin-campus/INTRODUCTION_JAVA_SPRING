package fr.le_campus_numerique.intro_java_spring.components;

import fr.le_campus_numerique.intro_java_spring.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.intro_java_spring.interfaces.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ComponentScan("game.tictactoe")
public class TicTacToePlugin implements GamePlugin {

    @Autowired
    private MessageSource msg;

    @Value("${game.tictactoe.default-player-count}")
    private int playerCount;

    @Value("${game.tictactoe.name}")
    private String gameName;

    @Override
    public String getName(Locale locale) {
        return msg.getMessage(gameName, null, locale);
    }

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }

}
