package yapp.buddycon.app.user.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.user.application.port.in.DeleteUserUsecase;

@Tag(name = "유저 삭제", description = "유저 삭제 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class DeleteUserController {

  private final DeleteUserUsecase usecase;

  @Operation(summary = "자체 회원탈퇴")
  @DeleteMapping("/me")
  public ResponseEntity<ResponseBody<Void>> deleteUser(
      @Parameter(hidden = true) AuthUser user) {
    usecase.delete(user.id());
    return ApiResponse.success("회원 탈퇴 처리 되었습니다.");
  }

}
