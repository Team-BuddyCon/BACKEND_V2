package yapp.buddycon.app.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

@Component
@RequiredArgsConstructor
public class SignUpDecider {

  private final UserQueryStorage userQueryStorage;
  private final UserCommandStorage userCommandStorage;
  private final OAuthUserInfoApi oAuthUserInfoApi;

  public User decide(String oauthAccessToken) {
    OAuthMemberInfo memberInfo = oAuthUserInfoApi.call(oauthAccessToken);
    Long clientId = memberInfo.id();
    return userQueryStorage.findByClientId(clientId).orElseGet(() -> userCommandStorage.save(new User(null, clientId)));
  }
}
