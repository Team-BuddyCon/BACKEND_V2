package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.common.s3.application.port.out.ImageUploader;
import yapp.buddycon.app.gifticon.application.service.CreateGifticonService;
import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateGifticonServiceTest {

    @Mock
    GifticonCommandStorage gifticonCommandStorage;

    @Mock
    ImageUploader imageUploader;

    @InjectMocks
    CreateGifticonService service;

    @Test
    void 정상적으로_기프티콘을_저장한다() {

        // given
        final var dto = new GifticonCreationDto("name", LocalDate.MAX, GifticonStore.GS25, null);
        final var userId = 1L;
        final var testImage = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                "testpng".getBytes()
        );
        when(imageUploader.upload(testImage)).thenReturn("s3 image url");

        // when
        service.createGifticon(dto, testImage, userId);

        // then
        verify(gifticonCommandStorage, times(1)).save(any());
    }

}