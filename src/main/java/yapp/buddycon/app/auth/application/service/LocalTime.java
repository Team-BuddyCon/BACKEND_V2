package yapp.buddycon.app.auth.application.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class LocalTime {
    public Instant getNow() {
        return Instant.now().atZone(ZoneId.systemDefault()).toInstant();
    }
}
