package yapp.buddycon.app.user.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByClientId(Long clientId);


    @Query(value = "SELECT * FROM users u WHERE u.client_id = :clientId AND u.deleted = false", nativeQuery = true)
    Optional<UserEntity> findByClientIdAndDeletedIsFalse(Long clientId);
}
