package yapp.buddycon.app.gifticon.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;
import yapp.buddycon.app.gifticon.application.port.in.UpdateGifticonUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.domain.Gifticon;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateGifticonService implements UpdateGifticonUsecase {

    private final GifticonQueryStorage queryStorage;
    private final GifticonCommandStorage commandStorage;

    @Override
    public void update(GifticonUpdateDto dto, Long userId) {
        Gifticon gifticon = queryStorage.getByGifticonIdAndUserId(dto.gifticonId(), userId);
        gifticon.modify(dto.name(), dto.memo(), dto.expireDate(), dto.store());
        commandStorage.save(gifticon);
    }
}
