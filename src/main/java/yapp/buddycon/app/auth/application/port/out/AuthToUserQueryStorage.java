package yapp.buddycon.app.auth.application.port.out;

import java.util.Optional;
import yapp.buddycon.app.user.domain.User;

public interface AuthToUserQueryStorage {

  Optional<User> findByClientId(Long clientId);
  Optional<User> findById(Long id);
}
