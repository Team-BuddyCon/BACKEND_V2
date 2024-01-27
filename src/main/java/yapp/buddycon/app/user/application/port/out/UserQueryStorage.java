package yapp.buddycon.app.user.application.port.out;

import yapp.buddycon.app.user.domain.User;

import java.util.Optional;

public interface UserQueryStorage {

    boolean existsByClientId(Long clientId);

    Optional<User> findByClientId(Long clientId);

    Optional<User> findById(Long id);
}
