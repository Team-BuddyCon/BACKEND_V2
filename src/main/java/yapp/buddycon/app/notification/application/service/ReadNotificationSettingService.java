package yapp.buddycon.app.notification.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.notification.adapter.NotificationSettingException;
import yapp.buddycon.app.notification.adapter.NotificationSettingException.NotificationExceptionCode;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationSettingUseCase;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadNotificationSettingService implements ReadNotificationSettingUseCase {

  private final NotificationSettingQueryStorage notificationSettingQueryStorage;

  @Override
  public NotificationSettingResponseDTO getMyNotificationSetting(Long userId) {
    NotificationSetting notificationSetting = notificationSettingQueryStorage.getByUserId(userId);
    return NotificationSettingResponseDTO.of(notificationSetting);
  }
}
