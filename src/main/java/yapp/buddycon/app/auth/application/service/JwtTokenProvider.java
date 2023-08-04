package yapp.buddycon.app.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;
import yapp.buddycon.app.auth.domain.User;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider implements TokenProvider {

  @Value("${security.jwt.token.access-token-expire-time}")
  private long ACCESS_TOKEN_EXPIRE_TIME;
  @Value("${security.jwt.token.refresh-token-expire-time}")
  private long REFRESH_TOKEN_EXPIRE_TIME;

  private final CacheStorage cacheStorage;
  private final TokenCreator tokenCreator;
  private final Time time;

  public Token provide(User user) {
    Date now = time.getNow();
    Date accessTokenExpiresIn = new Date(now.getTime()+ACCESS_TOKEN_EXPIRE_TIME);
    Date refreshTokenExpiresIn = new Date(now.getTime()+REFRESH_TOKEN_EXPIRE_TIME);

    Token token = tokenCreator.createToken(user, accessTokenExpiresIn, refreshTokenExpiresIn, now);
    cacheStorage.saveWithExpiration(user.id().toString(), token.refreshToken(), REFRESH_TOKEN_EXPIRE_TIME);

    return token;
  }

}