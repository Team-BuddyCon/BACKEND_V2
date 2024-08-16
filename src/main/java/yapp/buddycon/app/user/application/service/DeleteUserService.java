package yapp.buddycon.app.user.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.user.UserException;
import yapp.buddycon.app.user.UserException.UserExceptionCode;
import yapp.buddycon.app.user.application.port.in.DeleteUserUsecase;
import yapp.buddycon.app.user.application.port.out.UserCommandStorage;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;
import yapp.buddycon.app.user.application.port.out.UserToAuthCommandStorage;
import yapp.buddycon.app.user.domain.User;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUsecase {

  private final UserQueryStorage userQueryStorage;
  private final UserCommandStorage userCommandStorage;
  private final UserToAuthCommandStorage userToAuthCommandStorage;

  @Override
  public void delete(Long userId) {
    User user = userQueryStorage.findById(userId).orElseThrow(() ->
        new UserException(UserExceptionCode.USER_NOT_FOUND));

    // jwt 로그아웃
    userToAuthCommandStorage.delete(userId.toString());

    // 유저 삭제 처리
    User deletedUser = user.delete();
    userCommandStorage.save(deletedUser);
  }
}