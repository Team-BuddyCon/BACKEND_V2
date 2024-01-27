package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.user.domain.User;

public interface TokenProvider {

    TokenDto provide(User user);
    TokenDto reissue(User user, String refreshToken);
    AuthUser decrypt(String accessToken);
}
