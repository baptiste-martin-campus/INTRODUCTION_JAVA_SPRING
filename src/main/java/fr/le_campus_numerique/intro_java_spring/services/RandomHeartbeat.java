package fr.le_campus_numerique.intro_java_spring.services;

import fr.le_campus_numerique.intro_java_spring.interfaces.HeartbeatSensor;
import org.springframework.stereotype.Service;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    @Override
    public int get() {
        return (int) ((Math.random() * (240 - 30)) + 30);
    }
}
