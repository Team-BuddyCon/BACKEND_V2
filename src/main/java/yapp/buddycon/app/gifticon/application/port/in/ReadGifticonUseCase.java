package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.SingleGifticonResponseDto;

public interface ReadGifticonUseCase {

  Slice<GifticonResponseDTO> getUnavailableGifticons(Long userId, PagingDTO dto);

  Slice<GifticonResponseDTO> getAvailableGifticons(Long userId, SearchAvailableGifticonDTO dto);

  SingleGifticonResponseDto getGifticon(Long userId, Long gifticonId);

}
