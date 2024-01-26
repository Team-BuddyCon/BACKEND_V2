package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.AnnouncementNotiEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.AnnouncementNotiJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AnnouncementNotiJpaRepositoryTest {

  @Autowired
  private AnnouncementNotiJpaRepository announcementNotiJpaRepository;
  @Autowired
  private NotificationJpaRepository notificationJpaRepository;

  @Nested
  class findByIdAsResponseDTO {

    @Test
    void 요청한_공지사항이_존재하는_경우_DTO를_반환한다() {
      // given
      NotificationEntity notification1 = notificationJpaRepository.save(new NotificationEntity(null));
      AnnouncementNotiEntity announcementNoti = announcementNotiJpaRepository.save(
          new AnnouncementNotiEntity(null, notification1.getId(), "body", "body"));

      // when
      Optional<AnnouncementNotiResponseDTO> result = announcementNotiJpaRepository.findByIdAsResponseDTO(
          announcementNoti.getId());

      // then
      assertThat(result).isPresent();
      assertThat(result.get().announcementNotiId()).isEqualTo(announcementNoti.getId());
      assertThat(result.get().title()).isEqualTo(announcementNoti.getTitle());
      assertThat(result.get().body()).isEqualTo(announcementNoti.getBody());
    }

    @Test
    void 요청한_공지사항이_존재하지_않는_경우_빈_옵셔널을_반환한다() {
      // given

      // when
      Optional<AnnouncementNotiResponseDTO> result = announcementNotiJpaRepository.findByIdAsResponseDTO(1l);

      // then
      assertThat(result).isEmpty();
    }
  }

}
