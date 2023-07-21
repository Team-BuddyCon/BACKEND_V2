package yapp.buddycon.web.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

  @Test
  void 토큰을_생성할때_token_creator는_한번_invocation_된다(@Mock TokenCreator tokenCreator, @Mock Time time, @Mock CacheStorage cacheStorage) {
    // given
    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(cacheStorage, tokenCreator, time);
    User user = new User(1L, 12345678L);
    Date testTime = new Date();

    when(time.getNow()).thenReturn(testTime);
    when(tokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(tokenCreator, times(1)).createToken(user, testTime, testTime, testTime);
  }
}
