package yapp.buddycon.web.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;

public interface GifticonUseCase {

  Page<GifticonResponseDTO> getGifticons(SearchGifticonDTO dto);

}
