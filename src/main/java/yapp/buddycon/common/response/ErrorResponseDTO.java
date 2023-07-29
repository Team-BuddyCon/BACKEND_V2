package yapp.buddycon.common.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import yapp.buddycon.common.exception.CommonExceptionCode;

@Getter
@Builder
public class ErrorResponseDTO {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final String error;
  private final String code;
  private final String message;

  public static ResponseEntity<ErrorResponseDTO> toResponseEntity(CommonExceptionCode exceptionCode) {
    return ResponseEntity
        .status(exceptionCode.getHttpStatus())
        .body(ErrorResponseDTO.builder()
            .error(exceptionCode.getHttpStatus().name())
            .code(exceptionCode.getClass().getSimpleName())
            .message(exceptionCode.getMessage())
            .build()
        );
  }

}
