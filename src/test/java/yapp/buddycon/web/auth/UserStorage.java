package yapp.buddycon.web.auth;

interface UserStorage {
  boolean existsByClientId(Long clientId);

  User save(User user);
}
