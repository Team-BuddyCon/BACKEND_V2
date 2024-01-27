package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.user.domain.User;

public interface AuthToUserCommandStorage {

  User save(User user);

}
