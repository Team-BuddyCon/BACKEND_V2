package yapp.buddycon.app.gifticon.infra.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.app.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.response.GifticonResponseDTO;

public interface GifticonJpaCustomRepository {

  Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable);

}
