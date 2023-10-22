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
import yapp.buddycon.app.auth.application.service.AuthService;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.common.response.ResponseEntityApi;

@Tag(name = "로그인", description = "로그인 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        TokenDto tokenDto = authService.login(request);
        return ResponseEntityApi.successWithBody("로그인에 성공하였습니다.", tokenDto);
    }

}
