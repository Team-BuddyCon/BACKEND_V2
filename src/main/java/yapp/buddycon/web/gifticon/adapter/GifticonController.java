package yapp.buddycon.web.gifticon.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonDetailVO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.port.in.GifticonUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class GifticonController {

  private final GifticonUseCase gifticonUseCase;

  @GetMapping("")
  public Page<GifticonVO> getGifticons(@Valid SearchGifticonDTO dto) {
    return gifticonUseCase.getGifticons(dto);
  }

  @GetMapping("/{gifticon-id}")
  public GifticonDetailVO getGifticonDetail(@PathVariable("gifticon-id") long gifticonId) {
    return gifticonUseCase.getGifticonDetail(gifticonId);
  }
}