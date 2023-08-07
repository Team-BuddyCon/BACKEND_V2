package yapp.buddycon.app.auth.adapter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.auth.application.service.TokenCreator;
import yapp.buddycon.app.user.domain.User;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenCreator implements TokenCreator {

  private final JwtTokenSecretKey jwtTokenSecretKey;
  @Override
  public Token createToken(User user, Date accessTokenExpiresIn, Date refreshTokenExpiresIn, Date now) {
    var key = jwtTokenSecretKey.getSecretKey();

    var accessToken = Jwts.builder()
      .claim("id", user.id())
      .setIssuedAt(now)
      .setExpiration(accessTokenExpiresIn)
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();
    var refreshToken = Jwts.builder()
      .setExpiration(refreshTokenExpiresIn)
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();

    return new Token(accessToken, refreshToken);
  }
}
