package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;
import yapp.buddycon.app.auth.application.service.TokenCreator;
import yapp.buddycon.app.auth.application.service.JwtTokenProvider;
import yapp.buddycon.app.auth.application.service.Time;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {
  TokenCreator tokenCreator = Mockito.mock(TokenCreator.class);
  Time time = Mockito.mock(Time.class);
  CacheStorage cacheStorage = Mockito.mock(CacheStorage.class);
  JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(cacheStorage, tokenCreator, time);

  @Test
  void 토큰을_생성할때_token_creator는_한번_invocation_된다() {
    // given
    User user = new User(1L, 12345678L);
    Date testTime = new Date();

    when(time.getNow()).thenReturn(testTime);
    when(tokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(tokenCreator, times(1)).createToken(user, testTime, testTime, testTime);
  }

  @Test
  void 토큰을_생성할때_refresh_token을_한번_저장한다() {
    // given
    User user = new User(1L, 12345678L);
    Date testTime = new Date();

    when(time.getNow()).thenReturn(testTime);
    when(tokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(cacheStorage, times(1)).saveWithExpiration(anyString(), anyString(), anyLong());
  }
}
