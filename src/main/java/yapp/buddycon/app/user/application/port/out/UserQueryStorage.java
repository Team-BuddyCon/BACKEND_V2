package yapp.buddycon.app.user.application.port.out;

import java.util.Optional;
import yapp.buddycon.app.user.domain.User;

public interface UserQueryStorage {

    boolean existsByClientId(Long clientId);
    Optional<User> findById(Long userId);

}
