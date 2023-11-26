package yapp.buddycon.app.notification.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.notification.adapter.client.request.UpdateNotificationSettingDTO;
import yapp.buddycon.app.notification.application.port.in.UpdateNotificationSettingUseCase;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingCommandStorage;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateNotificationSettingService implements UpdateNotificationSettingUseCase {

  private final NotificationSettingQueryStorage notificationSettingQueryStorage;
  private final NotificationSettingCommandStorage notificationSettingCommandStorage;

  @Override
  public void updateNotificationSetting(UpdateNotificationSettingDTO dto, Long userId) {
    NotificationSetting notificationSetting = notificationSettingQueryStorage.getByUserId(userId);

    notificationSetting.update(dto.activated(), dto.fourteenDaysBefore(), dto.sevenDaysBefore(),
        dto.threeDaysBefore(), dto.oneDayBefore(), dto.theDay());

    notificationSettingCommandStorage.save(notificationSetting);
  }

}
