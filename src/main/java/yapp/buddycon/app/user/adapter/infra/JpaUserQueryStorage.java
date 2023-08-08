package yapp.buddycon.app.user.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

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
