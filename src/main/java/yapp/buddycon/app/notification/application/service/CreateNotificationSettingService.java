package yapp.buddycon.app.notification.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.notification.application.port.in.CreateNotificationSettingUseCase;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingCommandStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateNotificationSettingService implements CreateNotificationSettingUseCase {

  private final NotificationSettingCommandStorage notificationSettingCommandStorage;

  @Override
  public void create(Long userId) {
    NotificationSetting notificationSetting = NotificationSetting.builder()
      .userId(userId)
      .activated(true)
      .fourteenDaysBefore(false)
      .sevenDaysBefore(true)
      .threeDaysBefore(false)
      .oneDayBefore(true)
      .theDay(true)
      .lastCheckedAt(LocalDateTime.now())
      .build();

    notificationSettingCommandStorage.save(notificationSetting);
  }
}
