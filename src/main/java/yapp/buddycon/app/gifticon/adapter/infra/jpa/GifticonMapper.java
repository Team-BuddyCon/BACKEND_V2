package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.domain.Gifticon;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GifticonMapper {

    public Gifticon toGifticon(GifticonEntity entity) {
        return Gifticon.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .imageUrl(entity.getImageUrl())
                .name(entity.getName())
                .memo(entity.getMemo())
                .expireDate(entity.getExpireDate())
                .used(entity.isUsed())
                .gifticonStore(entity.getGifticonStore())
                .build();
    }

    public Optional<Gifticon> toGifticon(Optional<GifticonEntity> optionalEntity) {
        return optionalEntity.map(entity ->
                Gifticon.builder()
                        .id(entity.getId())
                        .userId(entity.getUserId())
                        .imageUrl(entity.getImageUrl())
                        .name(entity.getName())
                        .memo(entity.getMemo())
                        .expireDate(entity.getExpireDate())
                        .used(entity.isUsed())
                        .gifticonStore(entity.getGifticonStore())
                        .gifticonStoreCategory(entity.getGifticonStoreCategory())
                        .build());
    }

    public GifticonEntity toEntity(Gifticon gifticon) {
        return GifticonEntity.builder()
                .id(gifticon.id())
                .userId(gifticon.userId())
                .imageUrl(gifticon.imageUrl())
                .name(gifticon.name())
                .memo(gifticon.memo())
                .expireDate(gifticon.expireDate())
                .used(gifticon.used())
                .gifticonStore(gifticon.gifticonStore())
                .build();
    }
}
