package yapp.buddycon.app.notification.adapter;

import lombok.Getter;
import yapp.buddycon.app.common.response.BadRequestException;

public class AnnouncementNotiException extends BadRequestException {

  @Getter
  public enum AnnouncementNotiExceptionCode {
    ANNOUNCEMENT_NOTI_NOT_FOUND("공지사항을 찾을 수 없습니다"),
    ;

    private String message;

    AnnouncementNotiExceptionCode(String message) {
      this.message = message;
    }
  }

  public AnnouncementNotiException(AnnouncementNotiExceptionCode code) {
    super(code.message);
  }

}
