package yapp.buddycon.app.gifticon.application.port.in;

import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCountDto;

public interface GifticonCountUsecase {

    long countGifticons(long userId, GifticonCountDto dto);
}
