package yapp.buddycon.app.gifticon.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonEntity;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonMapper;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class JpaGifticonQueryStorage implements GifticonQueryStorage {

    private final GifticonJpaRepository gifticonJpaRepository;
    private final GifticonMapper mapper;

    @Override
    public Slice<GifticonResponseDTO> findAllUnavailableGifticons(long userId, Pageable pageable) {
        return gifticonJpaRepository.findAllByUsedIsTrueAndUserId(userId, pageable);
    }

    @Override
    public Slice<GifticonResponseDTO> findAllAvailableGifticons(
            long userId, GifticonStoreCategory gifticonStoreCategory, GifticonStore gifticonStore, Pageable pageable) {
        if (gifticonStoreCategory == null) {
            if (gifticonStore == null) {
                // Category X, Store X
                return gifticonJpaRepository.findAllByUsedIsFalseAndUserId(userId, pageable);
            } else {
                // Category X, Store O
                return gifticonJpaRepository.findAllByUsedIsFalseAndUserIdAndGifticonStore(userId, gifticonStore, pageable);
            }
        } else {
            if (gifticonStore == null) {
                // Category O, Store X
                return gifticonJpaRepository.findAllByUsedIsFalseAndUserIdAndGifticonStoreCategory(userId, gifticonStoreCategory, pageable);
            } else {
                // Category O, Store O
                return gifticonJpaRepository.findAllByUsedIsFalseAndUserIdAndGifticonStoreCategoryAndGifticonStore(userId, gifticonStoreCategory, gifticonStore, pageable);
            }
        }
    }

    @Override
    public GifticonResponseDTO findByGifticonIdAndUserId(long gifticonId, long userId) {
        GifticonEntity gifticon = gifticonJpaRepository.findByIdAndUserId(gifticonId, userId).orElseThrow(() -> new GifticonException(GifticonException.GifticonExceptionCode.GIFTICON_NOT_FOUND));
        return new GifticonResponseDTO(gifticon.getId(), gifticon.getImageUrl(), gifticon.getName(), gifticon.getMemo(), gifticon.getExpireDate(), gifticon.getGifticonStore(), gifticon.getGifticonStoreCategory());
    }

    @Override
    public Gifticon getByGifticonIdAndUserId(long gifticonId, long userId) {
        return mapper.toGifticon(
                gifticonJpaRepository.findByIdAndUserId(gifticonId, userId)
                        .orElseThrow(() -> new GifticonException(GifticonException.GifticonExceptionCode.GIFTICON_NOT_FOUND))
        );
    }

    @Override
    public Long countByUserIdAndUsedAndGifticonStoreCategoryAndExpireDate(
            long userId, Boolean used, GifticonStoreCategory gifticonStoreCategory, LocalDate toExpireDate) {
        return gifticonJpaRepository.countByUserIdAndUsedAndGifticonStoreCategoryAndExpireDate(userId, used, gifticonStoreCategory, toExpireDate);
    }

}
