package yapp.buddycon.app.auth.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.UserQueryStorage;
import yapp.buddycon.app.auth.domain.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUserQueryStorage implements UserQueryStorage {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    @Override
    public boolean existsByClientId(Long clientId) {
        return jpaUserRepository.existsByClientId(clientId);
    }


    @Override
    public Optional<User> findByClientId(Long clientId) {
        return mapper.toUser(jpaUserRepository.findByClientId(clientId));
    }
}
