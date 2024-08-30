package yapp.buddycon.app.user.adapter.jpa;

import org.springframework.stereotype.Component;
import yapp.buddycon.app.user.domain.User;

import java.util.Optional;

@Component
public class UserMapper {

    public User toUser(UserEntity entity) {
        return new User(entity.getId(), entity.getClientId(), entity.getNickname(), entity.getEmail(), entity.getGender(), entity.getAge(), entity.isDeleted());
    }

    public Optional<User> toUser(Optional<UserEntity> entity) {
        return entity.map(userEntity -> new User(userEntity.getId(), userEntity.getClientId(), userEntity.getNickname(), userEntity.getEmail(), userEntity.getGender(), userEntity.getAge(), userEntity.isDeleted()));
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(user.id(), user.clientId(), user.nickname(), user.email(), user.gender(), user.age(), user.deleted());
    }
}
