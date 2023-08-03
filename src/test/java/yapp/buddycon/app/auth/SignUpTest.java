package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.auth.application.port.out.UserStorage;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.service.SignUp;
import yapp.buddycon.app.auth.domain.User;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SignUpTest {

  @Test
  void 신규_로그인_회원은_db에_저장한다(@Mock UserStorage userStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userStorage.existsByClientId(memberInfo.id())).thenReturn(false);

    // when
    signUp.signUp(validAccessToken);

    // then
    verify(userStorage).save(new User(null, memberInfo.id()));
  }

  @Test
  void 기존_회원은_db에_저장하지않는다(@Mock UserStorage userStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userStorage.existsByClientId(memberInfo.id())).thenReturn(true);

    // when
    signUp.signUp(validAccessToken);

    // then
    verifyNoMoreInteractions(userStorage);
  }

}
