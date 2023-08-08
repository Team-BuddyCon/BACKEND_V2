package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import java.time.Instant;

public interface TokenCreator {
  Token createToken(User user, Instant accessTokenExpiresIn, Instant refreshTokenExpiresIn, Instant now);
}
