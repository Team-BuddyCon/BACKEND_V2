package yapp.buddycon.app.user.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByClientId(Long clientId);

    Optional<UserEntity> findByClientId(Long clientId);
}
