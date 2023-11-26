package yapp.buddycon.app.notification.application.port.out;

import yapp.buddycon.app.notification.domain.NotificationSetting;

public interface NotificationSettingCommandStorage {

  NotificationSetting save(NotificationSetting notificationSetting);

}
