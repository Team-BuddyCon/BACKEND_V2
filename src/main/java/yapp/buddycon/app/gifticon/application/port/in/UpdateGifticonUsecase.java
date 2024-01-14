package yapp.buddycon.app.gifticon.application.port.in;

import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;

public interface UpdateGifticonUsecase {

    void update(GifticonUpdateDto dto, Long gifticonId, Long userId);
    boolean updateUsed(boolean used, Long gifticonId, Long userId);
}
