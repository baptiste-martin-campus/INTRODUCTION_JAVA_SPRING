package fr.le_campus_numerique.intro_java_spring.controllers;

import fr.le_campus_numerique.intro_java_spring.interfaces.GameCatalog;
import fr.le_campus_numerique.intro_java_spring.interfaces.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HeartbeatController{

    @Autowired
    private HeartbeatSensor heartbeatSensor;

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/heartbeat")
    public int getHeartbeat() {
        return heartbeatSensor.get();
    }

    @GetMapping("/game-catalog")
    public Collection<String> getGameCatalog() {
        return gameCatalog.getGameIdentifiers();
    }
}
