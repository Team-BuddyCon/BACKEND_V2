package yapp.buddycon.app.gifticon.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.in.CreateGifticonUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.common.s3.application.port.out.ImageUploader;
import yapp.buddycon.app.gifticon.domain.Gifticon;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateGifticonService implements CreateGifticonUsecase {

    private final GifticonCommandStorage gifticonCommandStorage;
    private final ImageUploader imageUploader;

    @Override
    public long createGifticon(GifticonCreationDto dto, MultipartFile image, Long userId) {
        String imageUrl = imageUploader.upload(image);
        Gifticon gifticon = new Gifticon(null, userId, imageUrl, dto.name(), dto.memo(), dto.expireDate(), false, false, dto.store());

        Gifticon savedGifticon = gifticonCommandStorage.save(gifticon);
        return savedGifticon.getId();
    }
}
