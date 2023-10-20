package yapp.buddycon.app.gifticon.adapter.client.response;

import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import java.time.LocalDate;

public record GifticonResponseDTO(
    Long gifticonId,
    String imageUrl,
    String name,
    String memo,
    LocalDate expireDate,
    GifticonStore gifticonStore,
    GifticonStoreCategory gifticonStoreCategory
) {
}
