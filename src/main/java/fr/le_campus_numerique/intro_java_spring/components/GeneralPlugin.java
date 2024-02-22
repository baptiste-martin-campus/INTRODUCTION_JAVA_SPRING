package fr.le_campus_numerique.intro_java_spring.components;

import fr.le_campus_numerique.intro_java_spring.interfaces.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public abstract class GeneralPlugin implements GamePlugin{

    protected int playerCount;

    protected String gameName;

    @Autowired
    protected MessageSource msg;


    public abstract void setGameName(String gameName);

    @Override
    public String getName(Locale locale) {
        return msg.getMessage(this.gameName, null, locale);
    }

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }
}
