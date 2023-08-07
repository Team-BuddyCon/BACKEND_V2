package yapp.buddycon.app.auth.application.service;

import yapp.buddycon.app.user.domain.User;

import java.util.Date;

public interface TokenCreator {
  Token createToken(User user, Date accessTokenExpiresIn, Date refreshTokenExpiresIn, Date now);
}
