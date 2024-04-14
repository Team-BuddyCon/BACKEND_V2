package yapp.buddycon.app.auth.adapter.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.jwt.exception.InvalidTokenException;
import yapp.buddycon.app.auth.adapter.redis.RedisRefreshTokenStorage;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.user.domain.User;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider implements TokenProvider {

  @Value("${security.jwt.token.access-token-expire-time}")
  private long ACCESS_TOKEN_EXPIRE_TIME;
  @Value("${security.jwt.token.refresh-token-expire-time}")
  private long REFRESH_TOKEN_EXPIRE_TIME;

  private final RedisRefreshTokenStorage refreshTokenStorage;
  private final JwtTokenCreator jwtTokenCreator;
  private final JwtTokenDecryptor jwtTokenDecryptor;
  private final LocalTime time;

  @Override
  public TokenDto provide(User user, boolean isFirstLogin) {
    Instant now = time.getNow();
    Instant accessTokenExpiresIn = now.plus(Duration.ofMillis(ACCESS_TOKEN_EXPIRE_TIME));
    Instant refreshTokenExpiresIn = now.plus(Duration.ofMillis(REFRESH_TOKEN_EXPIRE_TIME));

    TokenDto tokenDto = jwtTokenCreator.createToken(user, accessTokenExpiresIn, refreshTokenExpiresIn, now, isFirstLogin);

    refreshTokenStorage.save(String.valueOf(user.id()), tokenDto.refreshToken(), REFRESH_TOKEN_EXPIRE_TIME);

    return tokenDto;
  }

  @Override
  public TokenDto
  reissue(User user, String refreshToken) {
    String storedRefreshToken = refreshTokenStorage.get(String.valueOf(user.id()));

    if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
      throw new InvalidTokenException("유효하지 않은 토큰입니다.");
    }
    return this.provide(user, false);
  }

  @Override
  public AuthUser decrypt(String accessToken) {
    return jwtTokenDecryptor.decrypt(accessToken);
  }

}
