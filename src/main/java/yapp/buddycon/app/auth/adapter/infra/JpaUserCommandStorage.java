package yapp.buddycon.app.auth.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.UserCommandStorage;
import yapp.buddycon.app.auth.domain.User;


@RequiredArgsConstructor
@Component
public class JpaUserCommandStorage implements UserCommandStorage {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toUser(jpaUserRepository.save(mapper.toEntity(user)));
    }

}
