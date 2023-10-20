package yapp.buddycon.app.gifticon.adapter.client.response;

import java.time.LocalDate;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStore;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

public record GifticonResponseDTO(
    Long gifticonId,
    String barcode,
    String imageUrl,
    String name,
    String memo,
    LocalDate expireDate,
    GifticonStore gifticonStore,
    GifticonStoreCategory gifticonStoreCategory
) {
}
