package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.web.multipart.MultipartFile;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;

public interface CreateGifticonUsecase {

    long createGifticon(GifticonCreationDto dto, MultipartFile image, Long userId);
}
