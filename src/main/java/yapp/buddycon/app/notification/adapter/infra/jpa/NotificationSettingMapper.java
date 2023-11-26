package yapp.buddycon.app.notification.adapter.infra.jpa;

import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@Component
public class NotificationSettingMapper {

  public NotificationSetting toNotificationSetting(NotificationSettingEntity entity) {
    return NotificationSetting.builder()
        .id(entity.getId())
        .userId(entity.getUserId())
        .activated(entity.isActivated())
        .fourteenDaysBefore(entity.isFourteenDaysBefore())
        .sevenDaysBefore(entity.isSevenDaysBefore())
        .threeDaysBefore(entity.isThreeDaysBefore())
        .oneDayBefore(entity.isOneDayBefore())
        .theDay(entity.isTheDay())
        .build();
  }

  public NotificationSettingEntity toEntity(NotificationSetting notificationSetting) {
    return NotificationSettingEntity.builder()
        .id(notificationSetting.getId())
        .userId(notificationSetting.getUserId())
        .activated(notificationSetting.isActivated())
        .fourteenDaysBefore(notificationSetting.isFourteenDaysBefore())
        .sevenDaysBefore(notificationSetting.isSevenDaysBefore())
        .threeDaysBefore(notificationSetting.isThreeDaysBefore())
        .oneDayBefore(notificationSetting.isOneDayBefore())
        .theDay(notificationSetting.isTheDay())
        .build();
  }
}
