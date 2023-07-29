package yapp.buddycon.common.exception;

import org.springframework.http.HttpStatus;

public interface CommonExceptionCode {

  HttpStatus getHttpStatus();

  String getMessage();
}
