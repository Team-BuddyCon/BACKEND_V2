package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingEntity;
import yapp.buddycon.app.notification.adapter.infra.jpa.NotificationSettingJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NotificationSettingJpaRepositoryTest {

  @Autowired
  private NotificationSettingJpaRepository notificationSettingJpaRepository;

  @Test
  void 사용자_아이디로_조회() {
    // given
    NotificationSettingEntity notificationSettingEntity =
        NotificationSettingEntity.builder()
            .userId(1l)
            .build();
    notificationSettingEntity = notificationSettingJpaRepository.save(notificationSettingEntity);

    // when
    Optional<NotificationSettingResponseDTO> result =
        notificationSettingJpaRepository.findAllByUserId(1L);

    // then
    assertThat(result).isNotEmpty();
    assertThat(result.get().activated()).isEqualTo(notificationSettingEntity.isActivated());
    assertThat(result.get().fourteenDaysBefore()).isEqualTo(notificationSettingEntity.isFourteenDaysBefore());
    assertThat(result.get().sevenDaysBefore()).isEqualTo(notificationSettingEntity.isSevenDaysBefore());
    assertThat(result.get().threeDaysBefore()).isEqualTo(notificationSettingEntity.isThreeDaysBefore());
    assertThat(result.get().oneDayBefore()).isEqualTo(notificationSettingEntity.isOneDayBefore());
    assertThat(result.get().theDay()).isEqualTo(notificationSettingEntity.isTheDay());
  }

}
