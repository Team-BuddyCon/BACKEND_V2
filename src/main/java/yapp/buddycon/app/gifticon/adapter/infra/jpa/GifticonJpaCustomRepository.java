package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;

public interface GifticonJpaCustomRepository {

  Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable);

}
