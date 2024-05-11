package yapp.buddycon.app.gifticon.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCountDto;
import yapp.buddycon.app.gifticon.application.port.in.GifticonCountUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GifticonCountService implements GifticonCountUsecase {

    private final GifticonQueryStorage queryStorage;

    @Override
    public long countGifticons(Long userId, GifticonCountDto dto) {
        LocalDate toExpireDate = dto.remainingDays() == null ? null : LocalDate.now().plusDays(dto.remainingDays());
        return queryStorage.countByUserIdAndUsedAndGifticonStoreCategoryAndExpireDate(
                userId, dto.used(), dto.gifticonStoreCategory(), toExpireDate);
    }
}
