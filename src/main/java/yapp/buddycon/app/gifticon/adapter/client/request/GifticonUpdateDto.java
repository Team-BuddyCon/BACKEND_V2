package yapp.buddycon.app.gifticon.adapter.client.request;

import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.time.LocalDate;

public record GifticonUpdateDto(
        Long gifticonId,
        String name,
        LocalDate expireDate,
        GifticonStore store,
        String memo
) {
}
