package yapp.buddycon.app.auth.application.port.out;

public interface CachePort<K, V> {
  void save(K key, V value);
  V get(K key);
  void save(K key, V value, long expireTime);
}
