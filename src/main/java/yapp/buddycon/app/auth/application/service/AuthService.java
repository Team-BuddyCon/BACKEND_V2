package yapp.buddycon.app.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final SignUpDecider signUpDecider;
    private final TokenProvider tokenProvider;
    @Transactional
    public Token login(String oauthAccessToken) {
        User user = signUpDecider.decide(oauthAccessToken);
        return tokenProvider.provide(user);
    }
}
