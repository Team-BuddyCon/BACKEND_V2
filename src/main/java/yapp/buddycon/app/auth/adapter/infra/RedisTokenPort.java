package yapp.buddycon.app.auth.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.application.port.out.CachePort;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
class RedisTokenPort implements CachePort {

  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public void save(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public Object get(String key) {
    return redisTemplate.opsForValue().get("RT:" + key);
  }

  @Override
  public void saveWithExpiration(String key, Object value, long expireTime) {
    redisTemplate.opsForValue().set("RT:" + key, value, expireTime, TimeUnit.MILLISECONDS);
  }
}
