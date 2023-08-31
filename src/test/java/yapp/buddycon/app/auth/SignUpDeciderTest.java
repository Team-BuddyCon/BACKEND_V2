package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.auth.adapter.LoginRequest;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.service.SignUpDecider;
import yapp.buddycon.app.user.domain.User;

import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SignUpDeciderTest {

  @Test
  void 신규_로그인_회원은_db에_저장한다(@Mock UserQueryStorage userQueryStorage, @Mock UserCommandStorage userCommandStorage, @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "oauthAccessToken";
    var signUp = new SignUpDecider(userQueryStorage, userCommandStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    var request = new LoginRequest(validAccessToken, "nickname", "email", "FEMALE", "10-20");
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.empty());

    // when
    signUp.decide(request);

    // then
    verify(userCommandStorage).save(new User(null, memberInfo.id(), request.nickname(), request.email(), request.gender(), request.age()));
  }

  @Test
  void 기존_회원은_db에_저장하지않는다(@Mock UserQueryStorage userQueryStorage, @Mock UserCommandStorage userCommandStorage,
                          @Mock OAuthUserInfoApi oAuthUserInfoApi) {
    // given
    var validAccessToken = "oauthAccessToken";
    var signUp = new SignUpDecider(userQueryStorage, userCommandStorage, oAuthUserInfoApi);
    var memberInfo = new OAuthMemberInfo(1L);
    var request = new LoginRequest(validAccessToken, "nickname", "email", "FEMALE", "10-20");
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.of(new User(1L, memberInfo.id(), request.nickname(), request.email(), request.gender(), request.age())));


    // when
    signUp.decide(request);

    // then
    verifyNoMoreInteractions(userCommandStorage);
  }

}



