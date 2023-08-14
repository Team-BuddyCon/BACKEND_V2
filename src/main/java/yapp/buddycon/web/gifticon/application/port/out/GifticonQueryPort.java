package yapp.buddycon.web.gifticon.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;

public interface GifticonQueryPort {

  Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable);

}
