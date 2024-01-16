package yapp.buddycon.app.notification.adapter.client.response;

import java.time.LocalDateTime;

public record AnnouncementNotiResponseDTO(
    Long announcementId,
    String title,
    String body,
    LocalDateTime createdAt
) {

}
