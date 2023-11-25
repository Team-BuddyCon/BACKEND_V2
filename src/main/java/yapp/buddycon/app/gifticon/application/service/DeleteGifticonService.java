package yapp.buddycon.app.gifticon.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.gifticon.application.port.in.DeleteGifticonUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteGifticonService implements DeleteGifticonUsecase {

    private final GifticonCommandStorage commandStorage;

    @Override
    public void delete(Long userId, Long gifticonId) {
        commandStorage.delete(userId, gifticonId);
    }
}
