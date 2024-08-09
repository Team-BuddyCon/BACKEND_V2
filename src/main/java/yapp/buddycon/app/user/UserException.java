package yapp.buddycon.app.user;

import lombok.Getter;
import yapp.buddycon.app.common.response.BadRequestException;

public class UserException extends BadRequestException {

  @Getter
  public enum UserExceptionCode {
    USER_NOT_FOUND("유저를 찾을 수 없습니다"),
    ;

    private String message;

    UserExceptionCode(String message) {
      this.message = message;
    }
  }

  public UserException(UserExceptionCode code) {
    super(code.message);
  }
}
