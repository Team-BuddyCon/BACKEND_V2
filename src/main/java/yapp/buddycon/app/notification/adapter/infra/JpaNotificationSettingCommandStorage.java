package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingMapper;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingCommandStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@RequiredArgsConstructor
@Component
public class JpaNotificationSettingCommandStorage implements NotificationSettingCommandStorage {

  private final NotificationSettingJpaRepository notificationSettingJpaRepository;
  private final NotificationSettingMapper mapper;

  @Override
  public NotificationSetting save(NotificationSetting notificationSetting) {
    NotificationSettingEntity entity = notificationSettingJpaRepository
        .save(mapper.toEntity(notificationSetting));
    return mapper.toNotificationSetting(entity);
  }

}
