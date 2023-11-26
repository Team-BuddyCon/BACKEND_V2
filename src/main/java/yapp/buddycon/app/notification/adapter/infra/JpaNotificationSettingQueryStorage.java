package yapp.buddycon.app.notification.adapter.infra;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingJpaRepository;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;

@RequiredArgsConstructor
@Component
public class JpaNotificationSettingQueryStorage implements NotificationSettingQueryStorage {

  private final NotificationSettingJpaRepository notificationSettingJpaRepository;

  @Override
  public Optional<NotificationSettingResponseDTO> findByUserId(Long userId) {
    return notificationSettingJpaRepository.findByUserId(userId);
  }
}
