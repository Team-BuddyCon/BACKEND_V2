package yapp.buddycon.app.auth.adapter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String oauthAccessToken,
        @NotBlank
        String nickname,
        @Email
        String email,
        String gender,
        String age
) {
}
