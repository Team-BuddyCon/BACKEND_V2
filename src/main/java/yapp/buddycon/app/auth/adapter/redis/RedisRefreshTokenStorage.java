package yapp.buddycon.app.auth.adapter.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;

import java.util.concurrent.TimeUnit;
import yapp.buddycon.app.user.application.port.out.UserToAuthCommandStorage;

@RequiredArgsConstructor
@Component
public class RedisRefreshTokenStorage implements CacheStorage<String, String>,
    UserToAuthCommandStorage<String, String> {

  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public String get(String key) {
    return redisTemplate.opsForValue().get("RT:" + key);
  }

  @Override
  public void save(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public void save(String key, String value, long expireTime) {
    redisTemplate.opsForValue().set("RT:" + key, value, expireTime, TimeUnit.MILLISECONDS);
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete("RT:" + key);
  }
}
