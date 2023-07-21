package yapp.buddycon.web.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SignUpTest {

  @Test
  void 신규_로그인_회원은_db에_저장한다(@Mock UserStorage userStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userStorage, oAuthUserInfoApi);
    var oAuthResponse = new OAuthResponse(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(oAuthResponse);
    when(userStorage.existsByClientId(oAuthResponse.clientId())).thenReturn(false);

    // when
    signUp.signUp(validAccessToken);

    // then
    verify(userStorage).save(new User(null, oAuthResponse.clientId()));
  }

  @Test
  void 기존_회원은_db에_저장하지않는다(@Mock UserStorage userStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userStorage, oAuthUserInfoApi);
    var oAuthResponse = new OAuthResponse(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(oAuthResponse);
    when(userStorage.existsByClientId(oAuthResponse.clientId())).thenReturn(true);

    // when
    signUp.signUp(validAccessToken);

    // then
    verifyNoMoreInteractions(userStorage);
  }

}
