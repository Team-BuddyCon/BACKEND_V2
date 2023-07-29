package yapp.buddycon.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yapp.buddycon.common.response.ErrorResponseDTO;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {CommonException.class})
  protected ResponseEntity handleCommonException(CommonException e) {
    log.warn("handleCommonException : {}", e.toString());
    return ErrorResponseDTO.toResponseEntity(e.getCommonExceptionCode());
  }

}
