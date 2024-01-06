package yapp.buddycon.app.event;

import java.time.LocalDateTime;

public record NotificationCheckingEvent(
    Long userId,
    LocalDateTime checkedAt
) {

}
