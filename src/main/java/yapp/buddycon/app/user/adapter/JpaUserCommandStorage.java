package yapp.buddycon.app.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.user.adapter.jpa.JpaUserRepository;
import yapp.buddycon.app.user.adapter.jpa.UserMapper;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.domain.User;


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
