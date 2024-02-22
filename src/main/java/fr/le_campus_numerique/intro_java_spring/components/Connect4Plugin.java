package fr.le_campus_numerique.intro_java_spring.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("game.connect4")
public class Connect4Plugin extends GeneralPlugin {

    @Value("${game.connect4.name}")
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Value("${game.connect4.default-player-count}")
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

}
