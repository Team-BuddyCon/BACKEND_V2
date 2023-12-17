package yapp.buddycon.app.gifticon.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.gifticon.application.port.in.DeleteGifticonUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.domain.Gifticon;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteGifticonService implements DeleteGifticonUsecase {

    private final GifticonQueryStorage queryStorage;
    private final GifticonCommandStorage commandStorage;

    @Override
    public void delete(Long userId, Long gifticonId) {
        Gifticon gifticon = queryStorage.getByGifticonIdAndUserId(gifticonId, userId);
        gifticon.delete();
        commandStorage.save(gifticon);
    }
}