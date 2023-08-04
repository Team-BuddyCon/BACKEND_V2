package yapp.buddycon.app.auth.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;

@Component
class KakaoOAuthUserInfoApi implements OAuthUserInfoApi {

  private final WebClient webClient = WebClient.create("https://kapi.kakao.com/v2/user/me");

  @Override
  public OAuthMemberInfo call(String accessToken) {

    return webClient.get()
            .headers(header -> {
              header.setBearerAuth(accessToken);
            })
            .retrieve()
            .bodyToMono(Kakao.class)
            .onErrorMap((error) -> new OAuthRequestException("invalid access token"))
            .map(Kakao::toOAuthMember)
            .block();
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  record Kakao(
          Long id
  ){
    public OAuthMemberInfo toOAuthMember(){
      return new OAuthMemberInfo(id);
    }
  }
}
