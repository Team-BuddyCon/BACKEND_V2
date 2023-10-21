package yapp.buddycon.app.gifticon.adapter.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.ReadGifticonUseCase;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.request.PagingDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class ReadGifticonController {

  private final ReadGifticonUseCase gifticonUseCase;

  @GetMapping("/unavailable")
  public Slice<GifticonResponseDTO> getUnavailableGifticons(
      AuthUser authUser, @Valid PagingDTO dto) {
    return gifticonUseCase.getUnavailableGifticons(authUser.id(), dto);
  }

  @GetMapping("/available")
  public Slice<GifticonResponseDTO> getAvailableGifticons(
      AuthUser authUser, @Valid SearchAvailableGifticonDTO dto) {
    return gifticonUseCase.getAvailableGifticons(authUser.id(), dto);
  }

  @GetMapping("/{gifticon-id}")
  public ResponseEntity<?> getGifticon(
      AuthUser authUser, @PathVariable("gifticon-id") long gifticonId) {
    return ApiResponse.successWithBody("기프티콘 목록을 성공적으로 조회하였습니다.",
        gifticonUseCase.getGifticon(authUser.id(), gifticonId));
  }

}