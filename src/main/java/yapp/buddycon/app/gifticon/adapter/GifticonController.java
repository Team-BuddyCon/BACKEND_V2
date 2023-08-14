package yapp.buddycon.app.gifticon.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.GifticonUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class GifticonController {

  private final GifticonUseCase gifticonUseCase;

  @GetMapping("")
  public Page<GifticonResponseDTO> getGifticons(@Valid SearchGifticonDTO dto) {
    return gifticonUseCase.getGifticons(dto);
  }

}