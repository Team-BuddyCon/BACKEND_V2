package yapp.buddycon.app.gifticon.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.application.port.in.DeleteGifticonUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteGifticonService implements DeleteGifticonUsecase {

    private final GifticonQueryStorage queryStorage;
    private final GifticonCommandStorage commandStorage;

    @Override
    public void delete(Long userId, Long gifticonId) {
        if(queryStorage.existByUserIdAndGifticonId(userId, gifticonId)) commandStorage.delete(userId, gifticonId);
        else throw new GifticonException(GifticonException.GifticonExceptionCode.GIFTICON_NOT_FOUND);
    }
}
