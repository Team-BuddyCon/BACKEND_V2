package yapp.buddycon.web.auth;

record Token(
  String accessToken,
  String refreshToken
) {
}
