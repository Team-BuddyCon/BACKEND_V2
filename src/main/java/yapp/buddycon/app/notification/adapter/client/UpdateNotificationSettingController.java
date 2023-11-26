package yapp.buddycon.app.notification.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.notification.adapter.client.request.UpdateNotificationSettingDTO;
import yapp.buddycon.app.notification.application.port.in.UpdateNotificationSettingUseCase;

@Tag(name = "내 알림 설정 수정", description = "내 알림 설정 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification-settings")
public class UpdateNotificationSettingController {

  private final UpdateNotificationSettingUseCase updateNotificationSettingUseCase;

  @Operation(summary = "내 알림 설정 수정")
  @PutMapping("/me")
  public ResponseEntity<ResponseBody> updateMyNotificationSetting(
      @Parameter(hidden = true) AuthUser authUser,
      @RequestBody @Valid UpdateNotificationSettingDTO dto) {
    updateNotificationSettingUseCase.updateNotificationSetting(dto, authUser.id());
    return ApiResponse.success("내 알림 설정 수정을 완료하였습니다.");
  }

}
