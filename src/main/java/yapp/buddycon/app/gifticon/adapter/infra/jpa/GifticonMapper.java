package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import yapp.buddycon.app.user.adapter.jpa.UserMapper;

@Component
@RequiredArgsConstructor
public class GifticonMapper {

    public Gifticon toGifticon(GifticonEntity entity) {
        return new Gifticon(entity.getId(), entity.getUserId(), entity.getImageUrl(), entity.getName(), entity.getMemo(), entity.getExpireDate(), entity.isUsed(), entity.getGifticonStore(), entity.getGifticonStoreCategory());
    }

    public GifticonEntity toEntity(Gifticon gifticon) {
        return new GifticonEntity(gifticon.id(), gifticon.userId(), gifticon.imageUrl(), gifticon.name(), gifticon.memo(), gifticon.expireDate(), gifticon.used(), gifticon.gifticonStore());
    }
}
