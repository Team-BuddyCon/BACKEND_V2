package yapp.buddycon.app.gifticon.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonMapper;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

import java.util.Optional;

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
      long userId, GifticonStoreCategory gifticonStoreCategory, Pageable pageable) {
    if (gifticonStoreCategory == null) {
      return gifticonJpaRepository.findAllByUsedIsFalseAndUserId(userId, pageable);
    } else {
      return gifticonJpaRepository.findAllByUsedIsFalseAndUserIdAndGifticonStoreCategory(userId, gifticonStoreCategory, pageable);
    }
  }

  @Override
  public Optional<GifticonResponseDTO> findByGifticonIdAndUserId(long gifticonId, long userId) {
    return gifticonJpaRepository.findByIdAndUserId(gifticonId, userId);
  }

}
