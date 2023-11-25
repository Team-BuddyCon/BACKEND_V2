package yapp.buddycon.app.gifticon.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import java.util.Optional;

public interface GifticonQueryStorage {

  Slice<GifticonResponseDTO> findAllUnavailableGifticons(long userId, Pageable pageable);

  Slice<GifticonResponseDTO> findAllAvailableGifticons(
          long userId, GifticonStoreCategory gifticonStoreCategory, Pageable pageable
  );

  Optional<GifticonResponseDTO> findByGifticonIdAndUserId(long gifticonId, long userId);

  Long countByUserIdAndUsed(long userId, boolean used);

}
