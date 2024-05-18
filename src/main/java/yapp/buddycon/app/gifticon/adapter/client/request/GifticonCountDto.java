package yapp.buddycon.app.gifticon.adapter.client.request;

import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

public record GifticonCountDto(
        Boolean used,
        GifticonStoreCategory gifticonStoreCategory,
        GifticonStore gifticonStore,
        Integer remainingDays
) {
}
