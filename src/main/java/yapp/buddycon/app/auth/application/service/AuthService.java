package yapp.buddycon.app.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.adapter.client.request.ReissueRequestDto;
import yapp.buddycon.app.auth.adapter.jwt.exception.InvalidTokenException;
import yapp.buddycon.app.auth.application.port.in.AuthUsecase;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
@Transactional
public class AuthService implements AuthUsecase {

    private final SignUpDecider signUpDecider;
    private final TokenProvider tokenProvider;
    private final CacheStorage cacheStorage;
    private final UserQueryStorage userQueryStorage;

    @Override
    public TokenDto login(LoginRequest request) {
        User user = signUpDecider.decide(request);
        return tokenProvider.provide(user);
    }

    @Override
    public void logout(Long userId) {
        cacheStorage.delete(userId.toString());
    }

    @Override
    public TokenDto reissue(ReissueRequestDto dto) {
        AuthUser authUser = tokenProvider.decrypt(dto.accessToken());

        User user = userQueryStorage.findById(authUser.id())
            .orElseThrow((() -> new InvalidTokenException("올바르지 않은 토큰입니다.")));

        return tokenProvider.reissue(user, dto.refreshToken());
    }

}
