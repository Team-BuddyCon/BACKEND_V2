package yapp.buddycon.app.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.application.port.in.AuthUsecase;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
@Transactional
public class AuthService implements AuthUsecase {

    private final SignUpDecider signUpDecider;
    private final TokenProvider tokenProvider;

    @Override
    public TokenDto login(LoginRequest request) {
        User user = signUpDecider.decide(request);
        return tokenProvider.provide(user);
    }
}
