package yapp.buddycon.app.auth.application.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.event.NotificationSettingCreationEvent;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
public class SignUpDecider {

  private final UserQueryStorage userQueryStorage;
  private final UserCommandStorage userCommandStorage;
  private final OAuthUserInfoApi oAuthUserInfoApi;
  private final ApplicationEventPublisher applicationEventPublisher;

  public User decide(LoginRequest request) {
    OAuthMemberInfo memberInfo = oAuthUserInfoApi.call(request.oauthAccessToken());
    Long clientId = memberInfo.id();
    Optional<User> byClientId = userQueryStorage.findByClientId(clientId);

    if (byClientId.isPresent()) {
      return byClientId.get();
    }

    User createdUser = userCommandStorage.save(
        new User(null, clientId, request.nickname(), request.email(), request.gender(),
            request.age()));

    applicationEventPublisher.publishEvent(new NotificationSettingCreationEvent(createdUser.id()));

    return createdUser;
  }
}
