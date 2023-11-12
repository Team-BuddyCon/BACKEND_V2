package yapp.buddycon.app.notification.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.notification.adapter.NotificationSettingException;
import yapp.buddycon.app.notification.adapter.NotificationSettingException.NotificationExceptionCode;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationSettingUseCase;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadNotificationSettingService implements ReadNotificationSettingUseCase {

  private final NotificationSettingQueryStorage notificationQueryStorage;

  @Override
  public NotificationSettingResponseDTO getMyNotificationSetting(Long userId) {
    return notificationQueryStorage.findByUserId(userId)
        .orElseThrow(() -> new NotificationSettingException(NotificationExceptionCode.NOTIFICATION_SETTING_NOT_FOUND));
  }
}
