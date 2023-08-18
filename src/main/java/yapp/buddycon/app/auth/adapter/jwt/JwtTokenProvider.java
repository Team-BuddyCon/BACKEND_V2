package yapp.buddycon.app.auth.adapter.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.redis.RedisRefreshTokenStorage;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
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
  private final LocalTime time;

  public Token provide(User user) {
    Instant now = time.getNow();
    Instant accessTokenExpiresIn = now.plus(Duration.ofMillis(ACCESS_TOKEN_EXPIRE_TIME));
    Instant refreshTokenExpiresIn = now.plus(Duration.ofMillis(REFRESH_TOKEN_EXPIRE_TIME));

    Token token = jwtTokenCreator.createToken(user, accessTokenExpiresIn, refreshTokenExpiresIn, now);

    refreshTokenStorage.save(String.valueOf(user.id()), token.refreshToken(), REFRESH_TOKEN_EXPIRE_TIME);

    return token;
  }

}
