package yapp.buddycon.app.user.application.port.out;

import yapp.buddycon.app.user.domain.User;

public interface UserCommandPort {

    User save(User user);
}
