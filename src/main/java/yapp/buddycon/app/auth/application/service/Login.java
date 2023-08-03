package yapp.buddycon.app.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.domain.User;

@Component
@RequiredArgsConstructor
public class Login {

    private final SignUp signUp;
    private final TokenProvider tokenProvider;
    @Transactional
    public Token login(String accessToken) {
        User user = signUp.signUp(accessToken);
        return tokenProvider.provide(user);
    }
}
