package yapp.buddycon;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenCreator;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.user.domain.User;

@SpringBootTest
public class JwtCreatorTest {

  @Autowired
  private JwtTokenCreator jwtTokenCreator;

  @Test
  void createToken() {
    User user = new User(1L, 123L, "nickname1", "email@email.com", "male", "10~20", false);

    Instant now = Instant.now();
    Instant accessTokenExpiresIn = now.plus(Duration.ofMillis(604800000));
    Instant refreshTokenExpiresIn = now.plus(Duration.ofMillis(604800000));

    TokenDto token = jwtTokenCreator.createToken(user, accessTokenExpiresIn, refreshTokenExpiresIn,
        now, false);

    System.out.println(token.accessToken());
  }
}
