package yapp.buddycon.app.gifticon.adapter;

import lombok.Getter;
import yapp.buddycon.app.common.response.BadRequestException;

public class GifticonException extends BadRequestException {

  @Getter
  public enum GifticonExceptionCode {
    GIFTICON_NOT_FOUND("기프티콘을 찾을 수 없습니다"),
    ;

    private String message;

    GifticonExceptionCode(String message) {
      this.message = message;
    }
  }

  public GifticonException(GifticonExceptionCode code) {
    super(code.message);
  }
}