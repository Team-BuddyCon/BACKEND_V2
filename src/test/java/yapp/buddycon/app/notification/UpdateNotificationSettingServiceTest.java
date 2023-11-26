package yapp.buddycon.app.notification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.notification.adapter.client.request.UpdateNotificationSettingDTO;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingCommandStorage;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;
import yapp.buddycon.app.notification.application.service.UpdateNotificationSettingService;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@ExtendWith(MockitoExtension.class)
public class UpdateNotificationSettingServiceTest {

  @Mock
  NotificationSettingCommandStorage notificationSettingCommandStorage;
  @Mock
  NotificationSettingQueryStorage notificationSettingQueryStorage;
  @InjectMocks
  UpdateNotificationSettingService service;

  @Test
  void 정상적으로_알림_설정을_수정한다() {
    // given
    final var dto = new UpdateNotificationSettingDTO(
        false, false, false, false, true, false);
    final var userId = 1L;

    NotificationSetting notificationSetting = mock(NotificationSetting.class);
    when(notificationSettingQueryStorage.getByUserId(userId)).thenReturn(notificationSetting);

    // when
    service.updateNotificationSetting(dto, userId);

    // then
    verify(notificationSetting, times(1)).update(false, false, false, false, true, false);
    verify(notificationSettingCommandStorage, times(1)).save(notificationSetting);
  }

}
