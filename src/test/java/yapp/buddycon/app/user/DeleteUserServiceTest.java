package yapp.buddycon.app.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.application.service.DeleteUserService;
import yapp.buddycon.app.user.domain.User;

@ExtendWith(MockitoExtension.class)
public class DeleteUserServiceTest {

  @Mock
  UserQueryStorage userQueryStorage;
  @Mock
  UserCommandStorage userCommandStorage;
  @InjectMocks
  DeleteUserService service;

  @Test
  void 유저_삭제시_삭제된_유저를_저장한다() {
    // given
    final long userId = 1L;
    User user = new User(userId, 123l, "", "", "", "", false);

    when(userQueryStorage.findById(userId)).thenReturn(Optional.of(user));

    // when
    service.delete(userId);

    // then
    ArgumentCaptor<User> savedUser = ArgumentCaptor.forClass(User.class);

    verify(userCommandStorage).save(savedUser.capture());
    assertEquals(savedUser.getValue().id(), userId);
    assertEquals(savedUser.getValue().deleted(), true);
  }
}
