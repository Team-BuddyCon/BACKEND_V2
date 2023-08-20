package yapp.buddycon.app.gifticon.domain;

import java.time.LocalDate;
import lombok.Builder;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStore;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

@Builder
public record Gifticon(
    Long id,
    String barcode,
    String imageUrl,
    String name,
    String memo,
    LocalDate expireDate,
    boolean used,
    GifticonStore gifticonStore,
    GifticonStoreCategory gifticonStoreCategory
) {

}
