package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

public interface GifticonJpaCustomRepository {

  Slice<GifticonResponseDTO> findAllByUsedIsTrue(Pageable pageable);

  Slice<GifticonResponseDTO> findAllAvailableGifticons(LocalDate today,
      GifticonStoreCategory gifticonStoreCategory, SearchGifticonSortType searchGifticonSortType,
      Pageable pageable);

}
