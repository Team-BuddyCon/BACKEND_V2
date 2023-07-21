package yapp.buddycon.web.auth;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
class JwtTokenSecretKey {

  @Value("security.jwt.token.secret-key")
  private String SECRET_KEY;

  public SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
  }
}
