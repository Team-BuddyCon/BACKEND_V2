package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import java.util.Optional;

@Component
public class GifticonMapper {

  public Gifticon toGifticon(GifticonEntity entity) {
    return Gifticon.builder()
        .id(entity.getId())
        .barcode(entity.getBarcode())
        .imageUrl(entity.getImageUrl())
        .name(entity.getName())
        .memo(entity.getMemo())
        .expireDate(entity.getExpireDate())
        .used(entity.isUsed())
        .gifticonStore(entity.getGifticonStore())
        .gifticonStoreCategory(entity.getGifticonStoreCategory())
        .build();
  }

  public Optional<Gifticon> toGifticon(Optional<GifticonEntity> optionalEntity) {
    return optionalEntity.map(entity ->
        Gifticon.builder()
            .id(entity.getId())
            .barcode(entity.getBarcode())
            .imageUrl(entity.getImageUrl())
            .name(entity.getName())
            .memo(entity.getMemo())
            .expireDate(entity.getExpireDate())
            .used(entity.isUsed())
            .gifticonStore(entity.getGifticonStore())
            .gifticonStoreCategory(entity.getGifticonStoreCategory())
            .build());
  }

}
