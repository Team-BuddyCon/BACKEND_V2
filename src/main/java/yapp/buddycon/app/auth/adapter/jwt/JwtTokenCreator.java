package yapp.buddycon.app.auth.adapter.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.user.domain.User;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenCreator {

  private final JwtTokenSecretKey jwtTokenSecretKey;

  public TokenDto createToken(User user, Instant accessTokenExpiresIn, Instant refreshTokenExpiresIn, Instant now) {
    var key = jwtTokenSecretKey.getSecretKey();

    var accessToken = Jwts.builder()
      .claim("id", user.id())
      .setIssuedAt(Date.from(now))
      .setExpiration(Date.from(accessTokenExpiresIn))
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();
    var refreshToken = Jwts.builder()
      .setExpiration(Date.from(refreshTokenExpiresIn))
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();

    return new TokenDto(accessToken, refreshToken, accessTokenExpiresIn.toEpochMilli());
  }
}
