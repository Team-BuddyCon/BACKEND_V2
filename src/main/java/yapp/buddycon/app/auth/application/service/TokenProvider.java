package yapp.buddycon.app.auth.application.service;

import yapp.buddycon.app.user.domain.User;

public interface TokenProvider {

    Token provide(User user);
}
