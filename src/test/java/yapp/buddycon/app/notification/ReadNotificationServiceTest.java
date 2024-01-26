package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.event.NotificationCheckingEvent;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.application.port.out.NotificationQueryStorage;
import yapp.buddycon.app.notification.application.port.out.NotificationSettingQueryStorage;
import yapp.buddycon.app.notification.application.service.ReadNotificationService;
import yapp.buddycon.app.notification.domain.NotificationSetting;

@ExtendWith(MockitoExtension.class)
public class ReadNotificationServiceTest {

  @Mock
  private NotificationQueryStorage notificationQueryStorage;
  @Mock
  private NotificationSettingQueryStorage notificationSettingQueryStorage;
  @Mock
  private ApplicationEventPublisher applicationEventPublisher;
  @InjectMocks
  private ReadNotificationService readService;

  @Nested
  class getNotifications {
    @BeforeEach
    void setUp() {
      NotificationSetting notificationSetting = mock(NotificationSetting.class);
      when(notificationSettingQueryStorage.getByUserId(anyLong())).thenReturn(notificationSetting);
      when(notificationSetting.getLastCheckedAt()).thenReturn(LocalDateTime.now());
    }

    @Test
    void 데이터가_없을_경우_빈_리스트를_반환한다() {
      // given
      when(notificationQueryStorage.findAllByUserId(anyLong(), any(), any())).thenReturn(
          new SliceImpl<>(Collections.emptyList())
      );

      // when
      Slice<NotificationResponseDTO> resultList = readService.getNotifications(1l, new PagingDTO());

      // then
      assertThat(resultList.getSize()).isEqualTo(0);
    }

    @Test
    void 알림_목록을_반환한다() {
      // given
      when(notificationQueryStorage.findAllByUserId(anyLong(), any(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new NotificationResponseDTO(1L, LocalDateTime.now(), 1L, "title1", null, null, null, null, false),
              new NotificationResponseDTO(2L, LocalDateTime.now(), null, null, 1L, 7, 1L, "name1", false)))
      );

      // when
      Slice<NotificationResponseDTO> resultList = readService.getNotifications(1l, new PagingDTO());

      // then
      assertThat(resultList.getSize()).isEqualTo(2);
    }

    @Test
    void 알림_확인_이벤트를_발생시킨다() {
      // given
      when(notificationQueryStorage.findAllByUserId(anyLong(), any(), any())).thenReturn(
          new SliceImpl<>(Collections.emptyList())
      );

      // when
      readService.getNotifications(1l, new PagingDTO());

      // then
      verify(applicationEventPublisher).publishEvent(any(NotificationCheckingEvent.class));
    }
  }

}
