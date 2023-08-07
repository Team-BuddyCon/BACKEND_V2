package yapp.buddycon.app.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {

    public static ResponseEntity<?> success(String message, Object body) {
        return ResponseEntity.ok(new ResponseBody(OK.value(), message, body));
    }


    public static ResponseEntity<?> badRequest(String message, Object body) {
        return ResponseEntity.badRequest().body(new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message, body));
    }
}
