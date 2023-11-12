package yapp.buddycon.app.notification.adapter.infra.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;

public interface NotificationSettingJpaRepository extends JpaRepository<NotificationSettingEntity, Long> {

  @Query(value = """
    select new yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO
      (n.activated, n.fourteenDaysBefore, n.sevenDaysBefore, n.threeDaysBefore, n.oneDayBefore, n.theDay)
    from NotificationSettingEntity n
    where n.userId = :userId
  """)
  Optional<NotificationSettingResponseDTO> findAllByUserId(Long userId);
}
