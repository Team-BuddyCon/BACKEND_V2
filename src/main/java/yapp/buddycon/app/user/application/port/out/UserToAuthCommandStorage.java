package yapp.buddycon.app.user.application.port.out;

public interface UserToAuthCommandStorage<K, V> {

  void delete(K key);
}
