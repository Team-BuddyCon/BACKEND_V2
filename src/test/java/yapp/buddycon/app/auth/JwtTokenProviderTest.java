package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenCreator;
import yapp.buddycon.app.auth.adapter.jwt.exception.InvalidTokenException;
import yapp.buddycon.app.auth.adapter.redis.RedisRefreshTokenStorage;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenProvider;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

  @Mock
  JwtTokenCreator jwtTokenCreator;
  @Mock
  LocalTime time;
  @Mock
  RedisRefreshTokenStorage refreshTokenStorage;
  @Spy
  @InjectMocks
  JwtTokenProvider jwtTokenProvider;

  User DEFAULT_USER = new User(1L, 12345678L, "nickname", "email", "FEMALE", "10-20", false);

  @Test
  void 토큰을_생성할때_token_creator는_한번_invocation_된다() {
    // given
    final var testTime = new LocalTime().getNow();

    when(time.getNow()).thenReturn(testTime);
    when(jwtTokenCreator.createToken(DEFAULT_USER, testTime, testTime, testTime, false)).thenReturn(new TokenDto("access", "refresh", 1l, false));

    // when
    jwtTokenProvider.provide(DEFAULT_USER, false);

    // then
    verify(jwtTokenCreator, times(1)).createToken(DEFAULT_USER, testTime, testTime, testTime, false);
  }

  @Test
  void 토큰을_생성할때_refresh_token을_한번_저장한다() {
    // given
    final var testTime = new LocalTime().getNow();

    when(time.getNow()).thenReturn(testTime);
    when(jwtTokenCreator.createToken(DEFAULT_USER, testTime, testTime, testTime, false)).thenReturn(new TokenDto("access", "refresh", 1l, false));

    // when
    jwtTokenProvider.provide(DEFAULT_USER, false);

    // then
    verify(refreshTokenStorage, times(1)).save(anyString(), anyString(), anyLong());
  }

  @Nested
  class reissue {

    @Test
    void storage에서_refreshToken과_요청한_값이_다를_경우_exception을_반환한다() {
      // given
      when(refreshTokenStorage.get(String.valueOf(DEFAULT_USER.id()))).thenReturn("differentRefreshToken");

      // when
      Throwable exception = assertThrows(InvalidTokenException.class, () -> {
        jwtTokenProvider.reissue(DEFAULT_USER, "refreshToken");
      });

      // then
      assertThat("유효하지 않은 토큰입니다.").isEqualTo(exception.getMessage());
    }

    @Test
    void storage에서_refreshToken과_요청한_값이_null일_경우_exception을_반환한다() {
      // given
      when(refreshTokenStorage.get(String.valueOf(DEFAULT_USER.id()))).thenReturn(null);

      // when
      Throwable exception = assertThrows(InvalidTokenException.class, () -> {
        jwtTokenProvider.reissue(DEFAULT_USER, "refreshToken");
      });

      // then
      assertThat("유효하지 않은 토큰입니다.").isEqualTo(exception.getMessage());
    }

    // Spy 객체 mocking 시 주의: https://stackoverflow.com/a/32944248
    @Test
    void storage에서_refreshToken과_요청한_값이_같을_경우_provide_메소드를_호출한다() {
      // given
      when(refreshTokenStorage.get(String.valueOf(DEFAULT_USER.id()))).thenReturn("refreshToken");

      TokenDto expect = new TokenDto("reissue accessToken", "reissue refreshToken", 1l, false);
      doReturn(expect).when(jwtTokenProvider).provide(DEFAULT_USER, false);

      // when
      TokenDto result = jwtTokenProvider.reissue(DEFAULT_USER, "refreshToken");

      // then
      verify(jwtTokenProvider, times(1)).provide(DEFAULT_USER, false);
      assertThat(result.accessToken()).isEqualTo(expect.accessToken());
      assertThat(result.refreshToken()).isEqualTo(expect.refreshToken());
    }

  }
}
