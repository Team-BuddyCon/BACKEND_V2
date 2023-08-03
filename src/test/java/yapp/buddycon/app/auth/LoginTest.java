package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.service.Login;
import yapp.buddycon.app.auth.application.service.SignUp;
import yapp.buddycon.app.auth.application.service.TokenProvider;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginTest {

    @Test
    void one_invocation_of_signup_when_login(@Mock SignUp signUp, @Mock TokenProvider tokenProvider) {
        // given
        var accessToken = "accessToken";
        var login = new Login(signUp, tokenProvider);

        // when
        login.login(accessToken);

        // then
        verify(signUp, times(1)).signUp(accessToken);

    }

}