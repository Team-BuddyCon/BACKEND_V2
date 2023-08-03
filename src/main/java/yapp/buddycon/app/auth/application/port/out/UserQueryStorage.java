package yapp.buddycon.app.auth.application.port.out;

import yapp.buddycon.app.auth.domain.User;

import java.util.Optional;

public interface UserQueryStorage {

    boolean existsByClientId(Long clientId);

    Optional<User> findByClientId(Long clientId);
}
