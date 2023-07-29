package yapp.buddycon.web.gifticon.adapter.in.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yapp.buddycon.common.exception.CommonException;
import yapp.buddycon.common.exception.CommonExceptionCode;

public class GifticonException extends CommonException {

  @RequiredArgsConstructor
  public enum GifticonExceptionCode implements CommonExceptionCode {

    GIFTICON_NOT_FOUND(HttpStatus.NOT_FOUND, "기프티콘을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
      return httpStatus;
    }

    @Override
    public String getMessage() {
      return message;
    }
  }

  public GifticonException(GifticonExceptionCode gifticonExceptionCode) {
    super(gifticonExceptionCode);
  }

}
