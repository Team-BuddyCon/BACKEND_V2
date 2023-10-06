package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.application.port.out.ImageUploader;
import yapp.buddycon.app.gifticon.application.service.CreateGifticonService;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.user.application.port.out.UserQueryStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateGifticonServiceTest {

    @Mock
    GifticonCommandStorage gifticonCommandStorage;

    @Mock
    UserQueryStorage userQueryStorage;

    @Mock
    ImageUploader imageUploader;

    @InjectMocks
    CreateGifticonService service;

    @Test
    void 정상적으로_기프티콘을_저장한다() throws IOException {

        // given
        final var dto = new GifticonCreationDto("name", LocalDate.now(), GifticonStore.GS25, null);
        final var userId = 1L;
        final var image = new MockMultipartFile("test-gifticon-img.png", new FileInputStream("src/test/resources/img/test-gifticon-img.png"));

        // when
        service.createGifticon(dto, image, userId);

        // then
        verify(gifticonCommandStorage, times(1)).save(any());
    }

}