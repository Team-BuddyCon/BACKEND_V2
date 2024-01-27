package yapp.buddycon.app.auth.adapter.client.request;

import jakarta.validation.constraints.NotEmpty;

public record ReissueRequestDto(
    @NotEmpty
    String accessToken,
    @NotEmpty
    String refreshToken
) {
}
