package yapp.buddycon.app.gifticon.domain;

import java.time.LocalDate;

public record Gifticon(
        Long id,
        Long userId,
        String imageUrl,
        String name,
        String memo,
        LocalDate expireDate,
        boolean used,
        GifticonStore gifticonStore,
        GifticonStoreCategory gifticonStoreCategory
) {
}