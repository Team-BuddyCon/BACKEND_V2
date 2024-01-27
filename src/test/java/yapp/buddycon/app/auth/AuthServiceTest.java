package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.port.out.AuthToUserQueryStorage;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;
import yapp.buddycon.app.auth.application.service.AuthService;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.application.service.SignUpDecider;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Test
    void one_invocation_of_signup_when_login(@Mock SignUpDecider signUpDecider, @Mock TokenProvider tokenProvider,
            @Mock CacheStorage cacheStorage, @Mock AuthToUserQueryStorage userQueryStorage) {
        // given
        var oauthAccessToken = "oauthAccessToken";
        var authService = new AuthService(signUpDecider, tokenProvider, cacheStorage, userQueryStorage);
        var request = new LoginRequest(oauthAccessToken, "nickname", "email", "FEMALE", "10-20");

        // when
        authService.login(request);

        // then
        verify(signUpDecider, times(1)).decide(request);

    }

}