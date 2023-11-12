package yapp.buddycon.app.notification.application.port.in;

import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;

public interface ReadNotificationSettingUseCase {

  NotificationSettingResponseDTO getMyNotificationSetting(Long userId);

}
