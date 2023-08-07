package yapp.buddycon.app.user.adapter.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.app.user.adapter.UserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByClientId(Long clientId);

    Optional<UserEntity> findByClientId(Long clientId);
}
