package yapp.buddycon.app.user.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.user.application.port.out.UserCommandPort;
import yapp.buddycon.app.user.domain.User;


@RequiredArgsConstructor
@Component
public class JpaUserCommandPort implements UserCommandPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toUser(jpaUserRepository.save(mapper.toEntity(user)));
    }

}
