package yapp.buddycon.app.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {

    public static <T> ResponseEntity<ResponseBody<T>> successWithBody(String message, T body) {
        return ResponseEntity.ok(new ResponseBody(OK.value(), message, body));
    }

    public static ResponseEntity<ResponseBody<Void>> success(String message) {
        return successWithBody(message, null);
    }

    public static <T> ResponseEntity<ResponseBody> serverError(String message, T body) {
        return ResponseEntity.internalServerError().body(new ResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, body));
    }

    public static <T> ResponseEntity<ResponseBody> badRequest(String message, T body) {
        return ResponseEntity.badRequest().body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), message, body));
    }

}
