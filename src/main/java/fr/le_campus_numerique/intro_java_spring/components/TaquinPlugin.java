package fr.le_campus_numerique.intro_java_spring.components;

import fr.le_campus_numerique.intro_java_spring.interfaces.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ComponentScan("game.taquin")
public class TaquinPlugin implements GamePlugin {

    @Autowired
    private MessageSource msg;

    @Value("${game.taquin.default-player-count}")
    private int playerCount;

    @Value("${game.taquin.name}")
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
