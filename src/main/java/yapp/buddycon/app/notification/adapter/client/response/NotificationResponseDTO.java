package yapp.buddycon.app.notification.adapter.client.response;

public record NotificationResponseDTO(
    Long notificationId,
    Long notificationAnnouncementId,
    String notificationAnnouncementTitle,
    Long notificationGifticonExpirationAlertId,
    Integer gifticonDaysLeft,
    Long gifticonId,
    String gifticonName
//    boolean checked
) {

}
