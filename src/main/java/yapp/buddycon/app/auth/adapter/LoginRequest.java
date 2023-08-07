package yapp.buddycon.app.auth.adapter;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String accessToken
) {
}
