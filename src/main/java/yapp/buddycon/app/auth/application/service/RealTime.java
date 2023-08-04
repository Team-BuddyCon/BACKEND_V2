package yapp.buddycon.app.auth.application.service;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RealTime implements Time{
    @Override
    public Date getNow() {
        return new Date();
    }
}
