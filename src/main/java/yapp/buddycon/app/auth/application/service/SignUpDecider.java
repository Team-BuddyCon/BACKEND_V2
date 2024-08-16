package yapp.buddycon.app.auth.application.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.AuthException;
import yapp.buddycon.app.auth.adapter.AuthException.AuthExceptionCode;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.application.port.out.AuthToUserCommandStorage;
import yapp.buddycon.app.auth.application.port.out.AuthToUserQueryStorage;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.event.NotificationSettingCreationEvent;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
public class SignUpDecider {

  private final AuthToUserQueryStorage userQueryStorage;
  private final AuthToUserCommandStorage userCommandStorage;
  private final OAuthUserInfoApi oAuthUserInfoApi;
  private final ApplicationEventPublisher applicationEventPublisher;

  public User decide(LoginRequest request) {
    OAuthMemberInfo memberInfo = oAuthUserInfoApi.call(request.oauthAccessToken());
    Long clientId = memberInfo.id();

    // 존재하는 유저라면 그대로 반환
    Optional<User> existingUser = userQueryStorage.findByClientId(clientId);
    if (existingUser.isPresent()) {
      User user = existingUser.get();

      // 탈퇴한 유저
      if (user.deleted()) {
        throw new AuthException(AuthExceptionCode.DELETED_USER);
      }
      return user;
    }

    // 존재하지 않는 유저라면 생성
    User createdUser = userCommandStorage.save(
        new User(null, clientId, request.nickname(), request.email(), request.gender(),
            request.age(), false));

    applicationEventPublisher.publishEvent(new NotificationSettingCreationEvent(createdUser.id()));
    return createdUser;
  }
}
