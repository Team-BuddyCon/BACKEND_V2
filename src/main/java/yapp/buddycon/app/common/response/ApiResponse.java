package yapp.buddycon.app.common.response;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

public class ApiResponse {

    public static ResponseEntity<?> success(String message, Object body) {
        return ResponseEntity.ok(new ResponseBody(OK.value(), message, body));
    }


}
