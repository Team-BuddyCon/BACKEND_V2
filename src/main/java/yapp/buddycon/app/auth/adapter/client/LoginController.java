package yapp.buddycon.app.auth.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.auth.application.port.in.AuthUsecase;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.common.response.ApiResponse;

@Tag(name = "로그인", description = "로그인 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthUsecase authUsecase;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ResponseBody> login(@RequestBody @Valid LoginRequest request) {
        TokenDto tokenDto = authUsecase.login(request);
        return ApiResponse.successWithBody("로그인에 성공하였습니다.", tokenDto);
    }

}
