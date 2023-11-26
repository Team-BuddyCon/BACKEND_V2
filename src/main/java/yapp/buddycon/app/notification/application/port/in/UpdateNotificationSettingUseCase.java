package yapp.buddycon.app.notification.application.port.in;

import yapp.buddycon.app.notification.adapter.client.request.UpdateNotificationSettingDTO;

public interface UpdateNotificationSettingUseCase {

  void updateNotificationSetting(UpdateNotificationSettingDTO dto, Long userId);

}
