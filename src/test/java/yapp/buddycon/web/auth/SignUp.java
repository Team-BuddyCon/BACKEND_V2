package yapp.buddycon.web.auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SignUp {

  private final UserStorage userStorage;
  private final OAuthUserInfoApi oAuthUserInfoApi;

  public void signUp(String accessToken) {
    OAuthResponse oAuthResponse = oAuthUserInfoApi.call(accessToken);
    Long clientId = oAuthResponse.clientId();
    if (!userStorage.existsByClientId(clientId)) {
      userStorage.save(new User(null, clientId));
    }
  }
}
