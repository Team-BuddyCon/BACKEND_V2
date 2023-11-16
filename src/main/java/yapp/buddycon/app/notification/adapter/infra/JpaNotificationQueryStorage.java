package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationJpaRepository;
import yapp.buddycon.app.notification.application.port.out.NotificationQueryStorage;

@RequiredArgsConstructor
@Component
public class JpaNotificationQueryStorage implements NotificationQueryStorage {

  private final NotificationJpaRepository notificationJpaRepository;

  @Override
  public Slice<NotificationResponseDTO> findAllByUserId(long userId, Pageable pageable) {
    return notificationJpaRepository.findAllByUserId(userId, pageable);
  }
}
