package yapp.buddycon.app.gifticon.application.port.in;

import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;

public interface UpdateGifticonUsecase {

    void update(GifticonUpdateDto dto, Long gifticonId, Long userId);
    void updateUsed(boolean used, Long gifticonId, Long userId);
}
