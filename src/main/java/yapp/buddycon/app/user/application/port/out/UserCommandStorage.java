package yapp.buddycon.app.user.application.port.out;

import yapp.buddycon.app.user.domain.User;

public interface UserCommandStorage {

  User save(User user);

}
