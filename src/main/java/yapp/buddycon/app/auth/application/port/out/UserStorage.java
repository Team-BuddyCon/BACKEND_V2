package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.domain.User;

import java.util.Optional;

public interface UserStorage {
  boolean existsByClientId(Long clientId);

  User save(User user);

  Optional<User> findByClientId(Long clientId);
}
