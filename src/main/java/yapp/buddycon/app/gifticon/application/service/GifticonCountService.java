package yapp.buddycon.app.gifticon.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.gifticon.application.port.in.GifticonCountUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GifticonCountService implements GifticonCountUsecase {

    private final GifticonQueryStorage queryStorage;

    @Override
    public Long countGifticons(Long userId, boolean used) {
        return queryStorage.countByUserIdAndUsed(userId, used);
    }
}
