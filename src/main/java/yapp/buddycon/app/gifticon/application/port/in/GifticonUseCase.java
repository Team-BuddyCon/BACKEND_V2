package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import yapp.buddycon.app.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.response.GifticonResponseDTO;

public interface GifticonUseCase {

  Page<GifticonResponseDTO> getGifticons(SearchGifticonDTO dto);

}
