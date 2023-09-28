package yapp.buddycon.app.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {

    public static ResponseEntity<?> success(String message, Object body) {
        return ResponseEntity.ok(new ResponseBody(OK.value(), message, body));
    }

    public static ResponseEntity<?> serverError(String message, Object body) {
        return ResponseEntity.internalServerError().body(new ResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, body));
    }

    public static ResponseEntity<?> badRequest(String message, Object body) {
        return ResponseEntity.badRequest().body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), message, body));
    }

}
