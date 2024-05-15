package yapp.buddycon.app.gifticon.adapter.client.response;

import java.time.LocalDate;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

public record SingleGifticonResponseDto(
        Long gifticonId,
        String imageUrl,
        String name,
        String memo,
        LocalDate expireDate,
        GifticonStore gifticonStore,
        GifticonStoreCategory gifticonStoreCategory,
        boolean used
) {
}
