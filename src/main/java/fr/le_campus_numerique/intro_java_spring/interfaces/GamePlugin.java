package fr.le_campus_numerique.intro_java_spring.interfaces;

import java.util.Locale;

public interface GamePlugin {

    String getName(Locale locale);

    int getPlayerCount();

}
