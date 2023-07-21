package yapp.buddycon.web.auth;

interface OAuthUserInfoApi {
  OAuthResponse call(String accessToken);
}
