package yapp.buddycon.app.user.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.user.UserException;
import yapp.buddycon.app.user.UserException.UserExceptionCode;
import yapp.buddycon.app.user.application.port.in.DeleteUserUsecase;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.domain.User;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUsecase {

  private final UserQueryStorage userQueryStorage;
  private final UserCommandStorage userCommandStorage;

  @Override
  public void delete(Long userId) {
    User user = userQueryStorage.findById(userId).orElseThrow(() ->
        new UserException(UserExceptionCode.USER_NOT_FOUND));

    User deletedUser = user.delete();

    // todo jwt 로그아웃 처리

    userCommandStorage.save(deletedUser);
  }
}