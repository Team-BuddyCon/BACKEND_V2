package yapp.buddycon.app.auth.application.service;

public record TokenDto(
  String accessToken,
  String refreshToken,
  Long accessTokenExpiresIn
) {
}
