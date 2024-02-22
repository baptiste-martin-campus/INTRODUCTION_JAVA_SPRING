package fr.le_campus_numerique.intro_java_spring.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("game.taquin")
public class TaquinPlugin extends GeneralPlugin {

    @Value("${game.taquin.name}")
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Value("${game.taquin.default-player-count}")
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }




}
