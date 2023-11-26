package yapp.buddycon.app.notification.adapter;

import lombok.Getter;
import yapp.buddycon.app.common.response.BadRequestException;

public class NotificationSettingException extends BadRequestException {

  @Getter
  public enum NotificationExceptionCode {
    NOTIFICATION_SETTING_NOT_FOUND("알림 설정을 찾을 수 없습니다"),
    ;

    private String message;

    NotificationExceptionCode(String message) {
      this.message = message;
    }
  }

  public NotificationSettingException(NotificationExceptionCode code) {
    super(code.message);
  }

}
