package yapp.buddycon.app.auth.adapter;

import lombok.Getter;
import yapp.buddycon.app.common.response.BadRequestException;

public class AuthException extends BadRequestException {

  @Getter
  public enum AuthExceptionCode {
    DELETED_USER("탈퇴된 회원입니다."),
    ;

    private String message;

    AuthExceptionCode(String message) {
      this.message = message;
    }
  }

  public AuthException(AuthExceptionCode code) {
    super(code.message);
  }

}
