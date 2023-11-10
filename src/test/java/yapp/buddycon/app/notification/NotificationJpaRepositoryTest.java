package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonEntity;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.AnnouncementNotiEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.AnnouncementNotiJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.GifticonExpirationNotiEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.GifticonExpirationNotiJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationJpaRepository;
import yapp.buddycon.app.user.adapter.jpa.JpaUserRepository;
import yapp.buddycon.app.user.adapter.jpa.UserEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NotificationJpaRepositoryTest {

  @Autowired
  private NotificationJpaRepository notificationRepository;
  @Autowired
  private GifticonExpirationNotiJpaRepository gifticonExpirationNotiRepository;
  @Autowired
  private AnnouncementNotiJpaRepository announcementNotiRepository;
  @Autowired
  private JpaUserRepository userRepository;
  @Autowired
  private GifticonJpaRepository gifticonJpaRepository;

  @Nested
  class findAllByUserId {

    @BeforeEach
    void dataInit() {
      userRepository.save(new UserEntity(1L, 123L, "nickname1", "aa@domain.com", "male", "20"));
      userRepository.save(new UserEntity(2L, 456L, "nickname2", "bb@domain.com", "female", "20"));
    }

    @Test
    void 기프티콘_만료_알림은_유저아이디를_기준으로_필터링된다() {
      // given
      gifticonJpaRepository.save(new GifticonEntity(1L, 1L, "url1", "name1", "memo1", LocalDate.now(), false, GifticonStore.STARBUCKS));
      gifticonJpaRepository.save(new GifticonEntity(2L, 2L, "url2", "name2", "memo2", LocalDate.now(), false, GifticonStore.MACDONALD));

      notificationRepository.save(new NotificationEntity(1L));
      notificationRepository.save(new NotificationEntity(2L));
      notificationRepository.save(new NotificationEntity(3L));

      gifticonExpirationNotiRepository.save(new GifticonExpirationNotiEntity(1L, 1L, 1L, 14));
      gifticonExpirationNotiRepository.save(new GifticonExpirationNotiEntity(2L, 2L, 1L, 7));
      gifticonExpirationNotiRepository.save(new GifticonExpirationNotiEntity(3L, 3L, 2L, 7));

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(1L, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void 공지사항을_포함한다() {
      // given
      notificationRepository.save(new NotificationEntity(1L));

      announcementNotiRepository.save(new AnnouncementNotiEntity(1L, 1L, "title", "body"));

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(1L, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void 조회된_결과가_없을_시_빈_리스트를_반환한다() {
      // given

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(1L, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(0);
    }

  }
}
