package yapp.buddycon.app.auth;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.client.request.ReissueRequestDto;
import yapp.buddycon.app.auth.adapter.jwt.exception.InvalidTokenException;
import yapp.buddycon.app.auth.application.port.out.AuthToUserQueryStorage;
import yapp.buddycon.app.auth.application.port.out.CacheStorage;
import yapp.buddycon.app.auth.application.service.AuthService;
import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.application.service.SignUpDecider;
import yapp.buddycon.app.auth.application.port.out.TokenProvider;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @InjectMocks
  private AuthService authService;
  @Mock
  private SignUpDecider signUpDecider;
  @Mock
  private TokenProvider tokenProvider;
  @Mock
  private CacheStorage cacheStorage;
  @Mock
  private AuthToUserQueryStorage userQueryStorage;

  @Test
  void one_invocation_of_signup_when_login() {
    // given
    var oauthAccessToken = "oauthAccessToken";
    var authService = new AuthService(signUpDecider, tokenProvider, cacheStorage, userQueryStorage);
    var request = new LoginRequest(oauthAccessToken, "nickname", "email", "FEMALE", "10-20");

    // when
    authService.login(request);

    // then
    verify(signUpDecider, times(1)).decide(request);
  }

  @Nested
  class reissue {

    @Test
    void accessToken_decrypt가_성공하고_존재하는_유저일_경우_재발행하여_반환한다() {
      // given
      ReissueRequestDto dto = new ReissueRequestDto("accessToken", "refreshToken");

      AuthUser authUser = new AuthUser(1l);
      when(tokenProvider.decrypt(dto.accessToken())).thenReturn(authUser);

      User user = new User(1l, 123l, "", "", "", "");
      when(userQueryStorage.findById(1l)).thenReturn(Optional.of(user));

      TokenDto tokenDto = new TokenDto("new accessToken", "new refreshToken", 1l);
      when(tokenProvider.reissue(any(), any())).thenReturn(tokenDto);

      // when
      TokenDto result = authService.reissue(dto);

      // then
      verify(tokenProvider, times(1)).reissue(user, dto.refreshToken());
      assertThat(result.accessToken()).isEqualTo(tokenDto.accessToken());
      assertThat(result.refreshToken()).isEqualTo(tokenDto.refreshToken());
    }

    @Test
    void accessToken_decrypt에서_반환한_유저가_storage에서_찾을_수_없는_경우_exception을_반환한다() {
      // given
      ReissueRequestDto dto = new ReissueRequestDto("accessToken", "refreshToken");

      AuthUser authUser = new AuthUser(1l);
      when(tokenProvider.decrypt(dto.accessToken())).thenReturn(authUser);

      when(userQueryStorage.findById(1l)).thenReturn(Optional.empty());

      // when
      Throwable exception = assertThrows(InvalidTokenException.class, () -> {
        authService.reissue(dto);
      });

      // then
      assertThat("올바르지 않은 토큰입니다.").isEqualTo(exception.getMessage());
      verifyNoMoreInteractions(tokenProvider);
    }
  }


}