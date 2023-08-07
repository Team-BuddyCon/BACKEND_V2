package yapp.buddycon.app.common.response;

import java.time.LocalDateTime;

public record ExceptionResponseBody(
        LocalDateTime timestamp,
        int status,
        String message,
        Object body
) {
}
