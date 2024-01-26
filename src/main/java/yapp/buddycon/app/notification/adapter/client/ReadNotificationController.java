package yapp.buddycon.app.notification.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationUseCase;

@Tag(name = "알림 조회", description = "알림 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class ReadNotificationController {

  private final ReadNotificationUseCase readUseCase;

  @Operation(summary = "알림 목록 조회")
  @GetMapping
  public ResponseEntity<?> getNotifications(
      @Parameter(hidden = true) AuthUser authUser, @Valid PagingDTO dto) {
    return ApiResponse.successWithBody("알림 목록을 성공적으로 조회하였습니다.",
        readUseCase.getNotifications(authUser.id(), dto));
  }

  @Operation(summary = "공지사항 단건 조회")
  @GetMapping("/announcement/{announcement-id}")
  public ResponseEntity<?> getAnnouncementNoti(
      @Parameter(hidden = true) AuthUser authUser, @PathVariable("announcement-id") long announcementId) {
    return ApiResponse.successWithBody("공지사항을 성공적으로 조회하였습니다.",
        readUseCase.getAnnouncementNoti(announcementId));
  }

}
