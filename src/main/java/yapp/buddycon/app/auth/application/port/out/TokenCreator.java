package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import java.util.Date;

public interface TokenCreator {
  Token createToken(User user, Date accessTokenExpiresIn, Date refreshTokenExpiresIn, Date now);
}
