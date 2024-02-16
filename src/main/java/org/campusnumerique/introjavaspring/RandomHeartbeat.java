package org.campusnumerique.introjavaspring;

import org.springframework.stereotype.Service;

@Service
public class RandomHeartbeat implements HeartbeatSensor{

    @Override
    public int get() {
        return (int) ((Math.random() * (240 - 30)) + 30);
    }
}
