package yapp.buddycon.app.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import yapp.buddycon.app.auth.application.port.out.AuthToUserCommandStorage;
import yapp.buddycon.app.auth.application.port.out.AuthToUserQueryStorage;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.event.NotificationSettingCreationEvent;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.service.SignUpDecider;
import yapp.buddycon.app.user.domain.User;

@ExtendWith(MockitoExtension.class)
class SignUpDeciderTest {

  @Mock
  AuthToUserQueryStorage userQueryStorage;
  @Mock
  AuthToUserCommandStorage userCommandStorage;
  @Mock
  OAuthUserInfoApi oAuthUserInfoApi;
  @Mock
  ApplicationEventPublisher applicationEventPublisher;
  @InjectMocks
  SignUpDecider signUpDecider;

  @Test
  void 신규_로그인_회원은_db에_저장한다() {
    // given
    var validAccessToken = "oauthAccessToken";
    var memberInfo = new OAuthMemberInfo(1L);
    var request = new LoginRequest(validAccessToken, "nickname", "email", "FEMALE", "10-20");
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.empty());
    User savedUser = new User(1l, memberInfo.id(), request.nickname(), request.email(), request.gender(), request.age(), false);
    when(userCommandStorage.save(any())).thenReturn(savedUser);

    // when
    signUpDecider.decide(request);

    // then
    verify(userCommandStorage).save(new User(null, memberInfo.id(), request.nickname(), request.email(), request.gender(), request.age(), false));
    verify(applicationEventPublisher).publishEvent(new NotificationSettingCreationEvent(1l));
  }

  @Test
  void 기존_회원은_db에_저장하지_않는다() {
    // given
    var validAccessToken = "oauthAccessToken";
    var memberInfo = new OAuthMemberInfo(1L);
    var request = new LoginRequest(validAccessToken, "nickname", "email", "FEMALE", "10-20");
    when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(memberInfo);
    when(userQueryStorage.findByClientId(memberInfo.id())).thenReturn(Optional.of(new User(1L, memberInfo.id(), request.nickname(), request.email(), request.gender(), request.age(), false)));

    // when
    signUpDecider.decide(request);

    // then
    verifyNoMoreInteractions(userCommandStorage);
    verifyNoMoreInteractions(applicationEventPublisher);
  }

}



