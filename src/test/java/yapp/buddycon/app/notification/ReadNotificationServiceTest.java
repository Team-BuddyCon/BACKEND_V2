package yapp.buddycon.app.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.application.port.out.NotificationQueryStorage;
import yapp.buddycon.app.notification.application.service.ReadNotificationService;

@ExtendWith(MockitoExtension.class)
public class ReadNotificationServiceTest {

  @Mock
  private NotificationQueryStorage queryStorage;
  @InjectMocks
  private ReadNotificationService readService;

  @Nested
  class getNotifications {

    @Test
    void 데이터가_없을_경우_빈_리스트를_반환한다() {
      // given
      when(queryStorage.findAll(anyLong(), any())).thenReturn(
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
      when(queryStorage.findAll(anyLong(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new NotificationResponseDTO(1L, 1L, "title1", null, null, null, null),
              new NotificationResponseDTO(2L, null, null, 1L, 7, 1L, "name1")))
      );

      // when
      Slice<NotificationResponseDTO> resultList = readService.getNotifications(1l, new PagingDTO());

      // then
      assertThat(resultList.getSize()).isEqualTo(2);
    }
  }

}
