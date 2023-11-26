package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingMapper;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@RequiredArgsConstructor
@Component
public class JpaNotificationSettingQueryStorage implements NotificationSettingQueryStorage {

  private final NotificationSettingJpaRepository notificationSettingJpaRepository;
  private final NotificationSettingMapper mapper;

  @Override
  public NotificationSetting getByUserId(Long userId) {
    NotificationSettingEntity entity = notificationSettingJpaRepository.getByUserId(userId);
    return mapper.toNotificationSetting(entity);
  }

}
