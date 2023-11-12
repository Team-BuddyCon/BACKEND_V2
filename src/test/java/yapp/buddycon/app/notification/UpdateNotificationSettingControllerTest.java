package yapp.buddycon.app.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import yapp.buddycon.app.common.MockAuthenticationArgumentResolver;
import yapp.buddycon.app.notification.adapter.client.UpdateNotificationSettingController;
import yapp.buddycon.app.notification.application.port.in.UpdateNotificationSettingUseCase;

@ExtendWith(MockitoExtension.class)
public class UpdateNotificationSettingControllerTest {

  private MockMvc mockMvc;
  private final MockAuthenticationArgumentResolver argumentResolver = new MockAuthenticationArgumentResolver();

  @Mock
  private UpdateNotificationSettingUseCase useCase;
  @InjectMocks
  private UpdateNotificationSettingController controller;

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new ExceptionHandlerExceptionResolver())
        .setCustomArgumentResolvers(argumentResolver)
        .build();
  }

  @Test
  void 수정요청시_200을_반환한다() throws Exception {
    // given
    final var dto = """
        {
          "activated": true,
          "fourteenDaysBefore": true,
          "sevenDaysBefore": true,
          "threeDaysBefore": true,
          "oneDayBefore": true,
          "theDay": true
        }
    """;
    doNothing().when(useCase).updateNotificationSetting(any(), anyLong());

    // when
    ResultActions result = mockMvc.perform(
        MockMvcRequestBuilders
            .put("/api/v1/notification-settings/me")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dto)
    );

    // then
    result.andExpect(status().isOk());
  }

  @Test
  void 수정요청시_필수값이_누락된_경우_400을_반환한다() throws Exception {
    // given
    final var dto = """
        {
          "theDay": true
        }
    """;

    // when
    ResultActions result = mockMvc.perform(
        MockMvcRequestBuilders
            .put("/api/v1/notification-settings/me")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dto)
    );

    // then
    result.andExpect(status().isBadRequest());
  }

}
