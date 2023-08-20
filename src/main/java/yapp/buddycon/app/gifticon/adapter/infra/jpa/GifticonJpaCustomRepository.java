package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;

public interface GifticonJpaCustomRepository {

  Slice<GifticonResponseDTO> findAllByUsedIsTrue(Pageable pageable);

  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndExpiredDateBefore(SearchAvailableGifticonDTO dto, Pageable pageable);

}
