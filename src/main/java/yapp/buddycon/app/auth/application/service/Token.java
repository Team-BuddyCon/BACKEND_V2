package yapp.buddycon.app.auth.application.service;

public record Token(
  String accessToken,
  String refreshToken
) {
}
