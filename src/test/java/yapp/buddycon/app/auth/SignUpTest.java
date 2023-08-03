package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.auth.application.port.out.UserCommandStorage;
import yapp.buddycon.app.auth.application.port.out.UserQueryStorage;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.service.SignUp;
import yapp.buddycon.app.auth.domain.User;

import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SignUpTest {

  @Test
  void 신규_로그인_회원은_db에_저장한다(@Mock UserQueryStorage userQueryStorage, @Mock UserCommandStorage userCommandStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userQueryStorage, userCommandStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.empty());

    // when
    signUp.signUp(validAccessToken);

    // then
    verify(userCommandStorage).save(new User(null, memberInfo.id()));
  }

  @Test
  void 기존_회원은_db에_저장하지않는다(@Mock UserQueryStorage userQueryStorage, @Mock UserCommandStorage userCommandStorage,
                          @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "accessToken";
    var signUp = new SignUp(userQueryStorage, userCommandStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.of(new User(1L, memberInfo.id())));


    // when
    signUp.signUp(validAccessToken);

    // then
    verifyNoMoreInteractions(userCommandStorage);
  }

}



