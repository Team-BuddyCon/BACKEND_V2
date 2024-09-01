package yapp.buddycon.app.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.AuthToUserQueryStorage;
import yapp.buddycon.app.user.adapter.jpa.JpaUserRepository;
import yapp.buddycon.app.user.adapter.jpa.UserMapper;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUserQueryStorage implements UserQueryStorage,
    AuthToUserQueryStorage {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    @Override
    public boolean existsByClientId(Long clientId) {
        return jpaUserRepository.existsByClientId(clientId);
    }

    @Override
    public Optional<User> findByClientIdAndDeletedIsFalse(Long clientId) {
        return mapper.toUser(jpaUserRepository.findByClientIdAndDeletedIsFalse(clientId));
    }

    @Override
    public Optional<User> findById(Long id) {
        return mapper.toUser(jpaUserRepository.findById(id));
    }
}
