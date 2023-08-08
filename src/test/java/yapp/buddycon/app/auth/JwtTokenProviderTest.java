package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.JwtTokenCreator;
import yapp.buddycon.app.auth.adapter.infra.RedisRefreshTokenStorage;
import yapp.buddycon.app.auth.adapter.JwtTokenProvider;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {
  @Mock
  JwtTokenCreator jwtTokenCreator;
  @Mock
  LocalTime time;
  @Mock
  RedisRefreshTokenStorage refreshTokenStorage;
  @InjectMocks
  JwtTokenProvider jwtTokenProvider;

  @Test
  void 토큰을_생성할때_token_creator는_한번_invocation_된다() {
    // given
    final var user = new User(1L, 12345678L);
    final var testTime = new LocalTime().getNow();

    when(time.getNow()).thenReturn(testTime);
    when(jwtTokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(jwtTokenCreator, times(1)).createToken(user, testTime, testTime, testTime);
  }

  @Test
  void 토큰을_생성할때_refresh_token을_한번_저장한다() {
    // given
    final var user = new User(1L, 12345678L);
    final var testTime = new LocalTime().getNow();

    when(time.getNow()).thenReturn(testTime);
    when(jwtTokenCreator.createToken(user, testTime, testTime, testTime)).thenReturn(new Token("access", "refresh"));

    // when
    jwtTokenProvider.provide(user);

    // then
    verify(refreshTokenStorage, times(1)).save(anyString(), anyString(), anyLong());
  }
}
