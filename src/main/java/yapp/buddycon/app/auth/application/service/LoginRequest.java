package yapp.buddycon.app.auth.application.service;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String oauthAccessToken,
        @NotBlank
        String nickname,
        @NotBlank
        String email,
        String gender,
        String age
) {
}
