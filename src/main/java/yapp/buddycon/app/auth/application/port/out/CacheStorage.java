package yapp.buddycon.app.auth.application.port.out;

public interface CacheStorage {
  void save(String key, Object value);
  Object get(String key);
  void saveWithExpiration(String key, Object value, long expireTime);
}
