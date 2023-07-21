package yapp.buddycon.web.auth;

import java.util.Date;

interface TokenCreator {
  Token createToken(User user, Date accessTokenExpiresIn, Date refreshTokenExpiresIn, Date now);
}
