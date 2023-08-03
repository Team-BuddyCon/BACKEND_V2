package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.domain.User;

public interface UserCommandStorage {

    User save(User user);
}
