package yapp.buddycon.app.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import yapp.buddycon.app.common.MockAuthenticationArgumentResolver;
import yapp.buddycon.app.notification.adapter.client.ReadNotificationController;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationUseCase;

@ExtendWith(MockitoExtension.class)
public class ReadNotificationControllerTest {

  private MockMvc mockMvc;
  private final String BASE_URL = "/api/v1/notifications";

  @Mock
  private ReadNotificationUseCase readUseCase;
  @InjectMocks
  private ReadNotificationController readController;
  private final MockAuthenticationArgumentResolver argumentResolver = new MockAuthenticationArgumentResolver();

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(readController)
        .setControllerAdvice(new ExceptionHandlerExceptionResolver())
        .setCustomArgumentResolvers(argumentResolver)
        .build();
  }

  @Nested
  class getNotifications {

    String API_PATH = "";

    @Test
    void 데이터가_없을_경우_빈_리스트를_반환한다() throws Exception {
      // given
      when(readUseCase.getNotifications(anyLong(), any())).thenReturn(
          new SliceImpl<>(Collections.emptyList()));

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + API_PATH)
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.body.size").value("0"));
    }

    @Test
    void 알림_목록을_반환한다() throws Exception {
      // given
      when(readUseCase.getNotifications(anyLong(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new NotificationResponseDTO(1L, 1L, "title1", null, null, null, null),
              new NotificationResponseDTO(2L, null, null, 1L, 7, 1L, "name1")
          )
      ));

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + API_PATH)
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.body.size").value("2"));

    }
  }

}
