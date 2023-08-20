package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;

public interface OAuthUserInfoApi {
  OAuthMemberInfo call(String accessToken);
}
