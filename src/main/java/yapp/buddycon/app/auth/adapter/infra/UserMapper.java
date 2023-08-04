package yapp.buddycon.app.auth.adapter.infra;

import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.UserEntity;
import yapp.buddycon.app.auth.domain.User;

import java.util.Optional;

@Component
public class UserMapper {

    public User toUser(UserEntity entity) {
        return new User(entity.getId(), entity.getClientId());
    }

    public Optional<User> toUser(Optional<UserEntity> entity) {
        return entity.map(userEntity -> new User(userEntity.getId(), userEntity.getClientId()));
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(user.id(), user.clientId());
    }
}