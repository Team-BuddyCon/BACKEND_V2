package yapp.buddycon.app.auth.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.infra.RedisRefreshTokenPort;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.auth.application.port.out.TokenCreator;
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

  private final RedisRefreshTokenPort refreshTokenPort;
  private final TokenCreator tokenCreator;
  private final LocalTime time;

  public Token provide(User user) {
    Instant now = time.getNow();
    Instant accessTokenExpiresIn = now.plus(Duration.ofMillis(ACCESS_TOKEN_EXPIRE_TIME));
    Instant refreshTokenExpiresIn = now.plus(Duration.ofMillis(REFRESH_TOKEN_EXPIRE_TIME));

    Token token = tokenCreator.createToken(user, accessTokenExpiresIn, refreshTokenExpiresIn, Instant.now());
    refreshTokenPort.save(String.valueOf(user.id()), token.refreshToken(), REFRESH_TOKEN_EXPIRE_TIME);

    return token;
  }

}
