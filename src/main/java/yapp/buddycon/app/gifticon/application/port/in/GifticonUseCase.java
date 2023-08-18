package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;

public interface GifticonUseCase {

  Page<GifticonResponseDTO> getGifticons(SearchGifticonDTO dto);

}
