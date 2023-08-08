package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.infra.RedisRefreshTokenPort;
import yapp.buddycon.app.auth.application.port.out.TokenCreator;
import yapp.buddycon.app.auth.adapter.JwtTokenProvider;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {
  TokenCreator tokenCreator = Mockito.mock(TokenCreator.class);
  LocalTime time = Mockito.mock(LocalTime.class);
  RedisRefreshTokenPort refreshTokenPort = Mockito.mock(RedisRefreshTokenPort.class);
  JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(refreshTokenPort, tokenCreator, time);

  @Test
  void 토큰을_생성할때_token_creator는_한번_invocation_된다() {
    // given
    final var user = new User(1L, 12345678L);
    final var testTime = new LocalTime().getNow();

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
    final var user = new User(1L, 12345678L);
    final var testTime = new LocalTime().getNow();

    when(time.getNow()).thenReturn(testTime);
    when(tokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(refreshTokenPort, times(1)).save(anyString(), anyString(), anyLong());
  }
}
