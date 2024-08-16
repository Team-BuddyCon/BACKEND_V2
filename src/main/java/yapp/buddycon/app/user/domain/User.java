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

  // delete method
  public User delete() {
    return new User(id, clientId, nickname, email, gender, age, true);
  }
}
