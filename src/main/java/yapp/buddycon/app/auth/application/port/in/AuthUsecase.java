package yapp.buddycon.app.auth.application.port.in;

import yapp.buddycon.app.auth.adapter.client.LoginRequest;
import yapp.buddycon.app.auth.adapter.client.request.ReissueRequestDto;
import yapp.buddycon.app.auth.application.service.TokenDto;

public interface AuthUsecase {

  TokenDto login(LoginRequest request);

  void logout(Long userId);

  TokenDto reissue(ReissueRequestDto dto);

}
