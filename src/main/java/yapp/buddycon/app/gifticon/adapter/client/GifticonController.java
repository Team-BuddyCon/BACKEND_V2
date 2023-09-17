package yapp.buddycon.app.gifticon.adapter.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.GifticonUseCase;
import yapp.buddycon.common.request.PagingDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class GifticonController {

  private final GifticonUseCase gifticonUseCase;

  @GetMapping("/unavailable")
  public Slice<GifticonResponseDTO> getUnavailableGifticons(
      OAuthMemberInfo oAuthMemberInfo, @Valid PagingDTO dto) {
    return gifticonUseCase.getUnavailableGifticons(oAuthMemberInfo, dto);
  }

  @GetMapping("/available")
  public Slice<GifticonResponseDTO> getAvailableGifticons(
      OAuthMemberInfo oAuthMemberInfo, @Valid SearchAvailableGifticonDTO dto) {
    return gifticonUseCase.getAvailableGifticons(oAuthMemberInfo, dto);
  }

}