package yapp.buddycon.app.notification.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationSettingUseCase;

@Tag(name = "내 알림 설정 조회", description = "내 알림 설정 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification-settings")
public class ReadNotificationSettingController {

  private final ReadNotificationSettingUseCase notificationUseCase;

  @Operation(summary = "내 알림 설정 조회")
  @GetMapping("/me")
  public ResponseEntity<ResponseBody<NotificationSettingResponseDTO>> getMyNotificationSetting(@Parameter(hidden = true) AuthUser authUser) {
    return ApiResponse.successWithBody("내 알림 설정을 성공적으로 조회하였습니다.",
        notificationUseCase.getMyNotificationSetting(authUser.id()));
  }

}
