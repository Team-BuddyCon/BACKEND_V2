package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import yapp.buddycon.app.notification.adapter.infra.jpa.GifticonExpirationAlertNotiEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.GifticonExpirationNotiJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationJpaRepository;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingJpaRepository;
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
  @Autowired
  private NotificationSettingJpaRepository notificationSettingJpaRepository;
  @Autowired
  private EntityManager em;

  @Nested
  class findAllByUserId {

    private UserEntity 사용자1;
    private UserEntity 사용자2;

    @BeforeEach
    void dataInit() {
      사용자1 = userRepository.save(new UserEntity(null, 123L, "nickname1", "aa@domain.com", "male", "20", false));
      사용자2 = userRepository.save(new UserEntity(null, 456L, "nickname2", "bb@domain.com", "female", "20", false));
    }

    @Test
    void 기프티콘_만료_알림은_유저아이디를_기준으로_필터링된다() {
      // given
      GifticonEntity 사용자1_기프티콘 = gifticonJpaRepository.save(
              new GifticonEntity(null, 사용자1.getId(), "url1", "name1", "memo1", LocalDate.now(), false, false, GifticonStore.STARBUCKS));
      GifticonEntity 사용자2_기프티콘 = gifticonJpaRepository.save(
              new GifticonEntity(null, 사용자2.getId(), "url2", "name2", "memo2", LocalDate.now(), false, false, GifticonStore.MACDONALD));

      NotificationEntity 알림1 = notificationRepository.save(new NotificationEntity(null));
      NotificationEntity 알림2 = notificationRepository.save(new NotificationEntity(null));
      NotificationEntity 알림3 = notificationRepository.save(new NotificationEntity(null));

      GifticonExpirationAlertNotiEntity 조회_대상1 = gifticonExpirationNotiRepository.save(
              new GifticonExpirationAlertNotiEntity(null, 알림1.getId(), 사용자1_기프티콘.getId(), 14));
      GifticonExpirationAlertNotiEntity 조회_대상2 = gifticonExpirationNotiRepository.save(
              new GifticonExpirationAlertNotiEntity(null, 알림2.getId(), 사용자1_기프티콘.getId(), 7));
      GifticonExpirationAlertNotiEntity 필터링_대상 = gifticonExpirationNotiRepository.save(
              new GifticonExpirationAlertNotiEntity(null, 알림3.getId(), 사용자2_기프티콘.getId(), 7));

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(사용자1.getId(), LocalDateTime.now(), PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void 공지사항을_포함한다() {
      // given
      NotificationEntity 알림 = notificationRepository.save(new NotificationEntity(null));

      AnnouncementNotiEntity 공지사항 = announcementNotiRepository.save(
              new AnnouncementNotiEntity(null, 알림.getId(), "title", "body"));

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(사용자1.getId(), LocalDateTime.now(), PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void 조회된_결과가_없을_시_빈_리스트를_반환한다() {
      // given

      // when
      Slice<NotificationResponseDTO> result = notificationRepository.findAllByUserId(사용자1.getId(), LocalDateTime.now(), PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(0);
    }

    @Test
    void 마지막_알림_확인_일시가_알림_생성_일시보다_이전인_알림은_확인_여부가_true이다() {
      // given
      LocalDateTime 조회_일시 = LocalDateTime.of(2020, 12, 1, 0, 0, 0);

      LocalDateTime 마지막_알림_확인_일시 = LocalDateTime.of(2020, 6, 6, 0, 0, 0);
      notificationSettingJpaRepository.save(
          new NotificationSettingEntity(null, 사용자1.getId(), true, true, true, true, true, true,
              마지막_알림_확인_일시)
      );

      LocalDateTime 알림_생성_일시1 = LocalDateTime.of(2019, 6, 6, 0, 0, 0);
      LocalDateTime 알림_생성_일시2 = LocalDateTime.of(2020, 3, 1, 0, 0, 0);
      LocalDateTime 알림_생성_일시3 = LocalDateTime.of(2020, 6, 6, 0, 0, 0);
      LocalDateTime 알림_생성_일시4 = LocalDateTime.of(2021, 6, 6, 0, 0, 0);
      em.createNativeQuery("INSERT INTO notification(created_at) VALUES (?1)")
          .setParameter(1, 알림_생성_일시1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .executeUpdate();
      em.createNativeQuery("INSERT INTO notification(created_at) VALUES (?1)")
          .setParameter(1, 알림_생성_일시2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .executeUpdate();
      em.createNativeQuery("INSERT INTO notification(created_at) VALUES (?1)")
          .setParameter(1, 알림_생성_일시3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .executeUpdate();
      em.createNativeQuery("INSERT INTO notification(created_at) VALUES (?1)")
          .setParameter(1, 알림_생성_일시4.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .executeUpdate();

      // when
      Slice<NotificationResponseDTO> result = notificationRepository
          .findAllByUserId(사용자1.getId(), 조회_일시, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(4);

      for (NotificationResponseDTO responseDTO : result.getContent()) {
        if (responseDTO.notificationCreatedAt().equals(알림_생성_일시1)) {
          assertThat(responseDTO.checked()).isTrue();
        } else if (responseDTO.notificationCreatedAt().equals(알림_생성_일시2)) {
          assertThat(responseDTO.checked()).isTrue();
        } else if (responseDTO.notificationCreatedAt().equals(알림_생성_일시3)) {
          assertThat(responseDTO.checked()).isTrue();
        } else if (responseDTO.notificationCreatedAt().equals(알림_생성_일시4)) {
          assertThat(responseDTO.checked()).isFalse();
        }
      }
    }

  }

}