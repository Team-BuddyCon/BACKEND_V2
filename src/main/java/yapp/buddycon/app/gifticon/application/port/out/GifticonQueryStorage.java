package yapp.buddycon.app.gifticon.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

public interface GifticonQueryStorage {

  Slice<GifticonResponseDTO> findAllUnavailableGifticons(long userId, Pageable pageable);

  Slice<GifticonResponseDTO> findAllAvailableGifticons(
          long userId, GifticonStoreCategory gifticonStoreCategory, GifticonStore gifticonStore, Pageable pageable);

  GifticonResponseDTO findByGifticonIdAndUserId(long gifticonId, long userId);

  Gifticon getByGifticonIdAndUserId(long gifticonId, long userId);

  Long countByUserIdAndUsed(long userId, boolean used);
}
