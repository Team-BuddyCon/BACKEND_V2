package yapp.buddycon.app.notification.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingCommandStorage;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@ExtendWith(MockitoExtension.class)
class CreateNotificationSettingServiceTest {

  @Mock
  NotificationSettingCommandStorage notificationSettingCommandStorage;
  @InjectMocks
  CreateNotificationSettingService createNotificationSettingService;


  @Test
  void 새로운_알림_설정을_추가한다() {
    // given
    Long userId = 123L;

    // when
    createNotificationSettingService.create(userId);

    // then
    verify(notificationSettingCommandStorage).save(any(NotificationSetting.class));
  }

  @Test
  public void 추가되는_알림_설정은_기본값을_가지고_있다() {
    // given
    Long userId = 123L;

    // when
    createNotificationSettingService.create(userId);

    // then
    ArgumentCaptor<NotificationSetting> argument = ArgumentCaptor.forClass(NotificationSetting.class);
    verify(notificationSettingCommandStorage).save(argument.capture());

    NotificationSetting capturedSetting = argument.getValue();
    assertThat(capturedSetting).isNotNull();
    assertThat(capturedSetting.getUserId()).isEqualTo(userId);
    assertThat(capturedSetting.isActivated()).isTrue();
    assertThat(capturedSetting.isFourteenDaysBefore()).isFalse();
    assertThat(capturedSetting.isSevenDaysBefore()).isTrue();
    assertThat(capturedSetting.isThreeDaysBefore()).isFalse();
    assertThat(capturedSetting.isOneDayBefore()).isTrue();
    assertThat(capturedSetting.isTheDay()).isTrue();
  }
}
