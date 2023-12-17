package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
                        .deleted(entity.isDeleted())
                        .gifticonStore(entity.getGifticonStore())
                        .build());
    }

    public GifticonEntity toEntity(Gifticon gifticon) {
        return GifticonEntity.builder()
                .id(gifticon.getId())
                .userId(gifticon.getUserId())
                .imageUrl(gifticon.getImageUrl())
                .name(gifticon.getName())
                .memo(gifticon.getMemo())
                .expireDate(gifticon.getExpireDate())
                .used(gifticon.isUsed())
                .deleted(gifticon.isDeleted())
                .gifticonStore(gifticon.getGifticonStore())
                .build();
    }
}
