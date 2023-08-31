package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.user.domain.User;

public interface TokenProvider {

    TokenDto provide(User user);
}
