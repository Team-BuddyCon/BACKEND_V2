package yapp.buddycon.app.gifticon.application.service;

import static yapp.buddycon.app.gifticon.adapter.GifticonException.GifticonExceptionCode.GIFTICON_IS_ALREADY_THAT_STATUS;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.common.response.BadRequestException;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.adapter.GifticonException.GifticonExceptionCode;
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
    public void update(GifticonUpdateDto dto, Long gifticonId, Long userId) {
        Gifticon gifticon = queryStorage.getByGifticonIdAndUserId(gifticonId, userId);
        gifticon.modify(dto.name(), dto.memo(), dto.expireDate(), dto.store());
        commandStorage.save(gifticon);
    }

    @Override
    public void updateUsed(boolean used, Long gifticonId, Long userId) {
        Gifticon gifticon = queryStorage.getByGifticonIdAndUserId(gifticonId, userId);
        if (gifticon.isUsed() == used) {
            throw new BadRequestException(GifticonExceptionCode.GIFTICON_IS_ALREADY_THAT_STATUS.getMessage());
        }
        gifticon.modifyUsed(used);
        commandStorage.save(gifticon);
    }
}
