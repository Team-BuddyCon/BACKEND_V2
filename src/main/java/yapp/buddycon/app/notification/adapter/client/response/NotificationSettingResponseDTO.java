package yapp.buddycon.app.notification.adapter.client.response;

import yapp.buddycon.app.notification.domain.NotificationSetting;

public record NotificationSettingResponseDTO(
    boolean activated,
    boolean fourteenDaysBefore,
    boolean sevenDaysBefore,
    boolean threeDaysBefore,
    boolean oneDayBefore,
    boolean theDay
) {

  public static NotificationSettingResponseDTO of(NotificationSetting notificationSetting) {
    return new NotificationSettingResponseDTO(
        notificationSetting.isActivated(),
        notificationSetting.isFourteenDaysBefore(),
        notificationSetting.isSevenDaysBefore(),
        notificationSetting.isThreeDaysBefore(),
        notificationSetting.isOneDayBefore(),
        notificationSetting.isTheDay());
  }

}
