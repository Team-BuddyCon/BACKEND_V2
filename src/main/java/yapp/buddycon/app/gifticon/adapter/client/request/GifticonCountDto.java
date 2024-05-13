package yapp.buddycon.app.gifticon.adapter.client.request;

import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

public record GifticonCountDto(
        Boolean used,
        GifticonStoreCategory gifticonStoreCategory,
        Integer remainingDays
) {
}
