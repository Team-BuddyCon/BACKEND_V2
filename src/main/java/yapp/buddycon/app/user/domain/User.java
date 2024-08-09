package yapp.buddycon.app.user.domain;

public record User(
  Long id,
  Long clientId,
  String nickname,
  String email,
  String gender,
  String age,
  boolean deleted
) {

}
