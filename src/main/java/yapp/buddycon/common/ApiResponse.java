package yapp.buddycon.common;

import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {

  record ApiResponseBodyWithData(
    int status,
    Object data,
    String message
  ) {
    @Builder
    public ApiResponseBodyWithData{
    }
  }

  record ApiResponseBody(
    int status,
    String message
  ) {
    @Builder
    public ApiResponseBody{
    }
  }

  public static ResponseEntity<?> success(String message) {
    return ResponseEntity.ok(ApiResponseBody.builder()
      .status(OK.value())
      .message(message)
      .build()
    );
  }

  public static ResponseEntity<?> successWithResponseBody(Object data, String message) {
    return ResponseEntity.ok(ApiResponseBodyWithData.builder()
      .status(OK.value())
      .message(message)
      .data(data)
      .build()
    );
  }
}
