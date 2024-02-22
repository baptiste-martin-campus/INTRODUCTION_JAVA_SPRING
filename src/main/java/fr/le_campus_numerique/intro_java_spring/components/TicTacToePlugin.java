package fr.le_campus_numerique.intro_java_spring.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("game.tictactoe")
public class TicTacToePlugin extends GeneralPlugin {
    @Value("${game.tictactoe.name}")
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Value("${game.tictactoe.default-player-count}")
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

}
