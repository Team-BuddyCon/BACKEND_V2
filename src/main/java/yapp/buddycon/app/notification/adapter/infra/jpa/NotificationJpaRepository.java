package yapp.buddycon.app.notification.adapter.infra.jpa;

import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {

  @Query(value = """
    SELECT new yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO
      (n.id, n.createdAt, an.id, an.title, gen.id, gen.daysLeft, g.id, g.name, (n.createdAt  < CAST(:lastCheckedAt AS localdatetime)))
    FROM NotificationEntity n
    LEFT OUTER JOIN AnnouncementNotiEntity an
      ON n.id = an.notificationId
    LEFT OUTER JOIN GifticonExpirationAlertNotiEntity gen
      ON n.id = gen.notificationId
    LEFT OUTER JOIN GifticonEntity g
      ON gen.gifticonId = g.id
    WHERE g.userId = :userId
       OR g.userId IS NULL
  """)
  Slice<NotificationResponseDTO> findAllByUserId(long userId, LocalDateTime lastCheckedAt, Pageable pageable);

}
