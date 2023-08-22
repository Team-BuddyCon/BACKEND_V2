package yapp.buddycon.app.gifticon.adapter.infra;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

@RequiredArgsConstructor
@Repository
public class JpaGifticonQueryStorage implements GifticonQueryStorage {

  private final GifticonJpaRepository gifticonJpaRepository;

  @Override
  public Slice<GifticonResponseDTO> findAllUsedGifticons(Pageable pageable) {
    return gifticonJpaRepository.findAllByUsedIsTrue(pageable);
  }

  @Override
  public Slice<GifticonResponseDTO> findAllAvailableGifticons(LocalDate today,
      GifticonStoreCategory gifticonStoreCategory, SearchGifticonSortType searchGifticonSortType,
      Pageable pageable) {
    return gifticonJpaRepository.findAllAvailableGifticons(
        today,
        gifticonStoreCategory,
        searchGifticonSortType,
        pageable);
  }
}
