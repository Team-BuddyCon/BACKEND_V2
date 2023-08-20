package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.common.request.PagingDTO;

public interface GifticonUseCase {

  Page<GifticonResponseDTO> getGifticons(SearchGifticonDTO dto);

  Slice<GifticonResponseDTO> getUnavailableGifticons(PagingDTO dto);

}
