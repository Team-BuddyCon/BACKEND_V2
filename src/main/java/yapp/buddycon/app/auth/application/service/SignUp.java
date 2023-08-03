package yapp.buddycon.app.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;
import yapp.buddycon.app.auth.application.port.out.UserStorage;
import yapp.buddycon.app.auth.domain.User;

@Component
@RequiredArgsConstructor
public class SignUp {

  private final UserStorage userStorage;
  private final OAuthUserInfoApi oAuthUserInfoApi;

  public User signUp(String accessToken) {
    OAuthMemberInfo memberInfo = oAuthUserInfoApi.call(accessToken);
    Long clientId = memberInfo.id();
    return userStorage.findByClientId(clientId).orElse(userStorage.save(new User(null, clientId)));
  }
}
