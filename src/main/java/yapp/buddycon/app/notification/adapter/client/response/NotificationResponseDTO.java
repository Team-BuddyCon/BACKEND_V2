package yapp.buddycon.app.notification.adapter.client.response;

public record NotificationResponseDTO(
    Long notificationId,
    Long announcementId,
    String announcementTitle,
    Long GifticonExpirationAlertId,
    Integer gifticonDaysLeft,
    Long gifticonId,
    String gifticonName
//    boolean checked
) {

}
