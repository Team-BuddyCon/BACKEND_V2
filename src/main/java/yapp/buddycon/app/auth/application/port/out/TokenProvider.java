package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

public interface TokenProvider {

    Token provide(User user);
}
