package yapp.buddycon.app.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.user.application.port.out.UserCommandPort;
import yapp.buddycon.app.user.application.port.out.UserQueryPort;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
public class SignUp {

  private final UserQueryPort userQueryPort;
  private final UserCommandPort userCommandPort;
  private final OAuthUserInfoApi oAuthUserInfoApi;

  public User signUp(String accessToken) {
    OAuthMemberInfo memberInfo = oAuthUserInfoApi.call(accessToken);
    Long clientId = memberInfo.id();
    return userQueryPort.findByClientId(clientId).orElseGet(() -> userCommandPort.save(new User(null, clientId)));
  }
}
