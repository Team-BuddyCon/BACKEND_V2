package yapp.buddycon.app.notification.adapter.client.response;

import java.time.LocalDateTime;

public record NotificationResponseDTO(
    Long notificationId,
    LocalDateTime notificationCreatedAt,
    Long announcementId,
    String announcementTitle,
    Long GifticonExpirationAlertId,
    Integer gifticonDaysLeft,
    Long gifticonId,
    String gifticonName,
    boolean checked
) {

}
