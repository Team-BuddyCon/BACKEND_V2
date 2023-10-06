package yapp.buddycon.app.gifticon.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

public interface GifticonQueryStorage {

  Slice<GifticonResponseDTO> findAllUnavailableGifticons(Pageable pageable);

  Slice<GifticonResponseDTO> findAllAvailableGifticons(
      GifticonStoreCategory gifticonStoreCategory,
      Pageable pageable
  );

}
