package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.application.service.UpdateGifticonService;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateGifticonServiceTest {

    @Mock
    GifticonQueryStorage queryStorage;
    @Mock
    GifticonCommandStorage commandStorage;
    @InjectMocks
    UpdateGifticonService service;

    @Test
    void 기프티콘_수정시_query가_한번_나간다() {
        final var gifticonId = 1L;
        final var userId = 1L;
        final var request = new GifticonUpdateDto("name", LocalDate.now(), GifticonStore.GS25, "memo");

        when(queryStorage.getByGifticonIdAndUserId(gifticonId, userId)).thenReturn(new Gifticon(gifticonId, userId, "imageUrl", "name", "memo", LocalDate.now(), false, false, GifticonStore.CU));
        service.update(request, gifticonId, userId);

        verify(queryStorage, times(1)).getByGifticonIdAndUserId(gifticonId, userId);
    }

    @Test
    void 기프티콘_수정시_command가_한번_나간다() {
        final var gifticonId = 1L;
        final var userId = 1L;
        final var gifticon = new Gifticon(gifticonId, userId, "imageUrl", "name", "memo", LocalDate.now(), false, false, GifticonStore.CU);
        final var request = new GifticonUpdateDto("name", LocalDate.now(), GifticonStore.GS25, "memo");

        when(queryStorage.getByGifticonIdAndUserId(gifticonId, userId)).thenReturn(gifticon);
        service.update(request, gifticonId, userId);

        verify(commandStorage, times(1)).save(any(Gifticon.class));
    }

    @Test
    void 기프티콘_상태_수정_성공시_save_command가_한번_수행된다() {
        final var gifticonId = 1L;
        final var userId = 1L;
        final var request = true;

        when(queryStorage.getByGifticonIdAndUserId(gifticonId, userId)).thenReturn(new Gifticon(gifticonId, userId, "imageUrl", "name", "memo", LocalDate.now(), false, false, GifticonStore.CU));
        service.updateUsed(request, gifticonId, userId);

        verify(commandStorage, times(1)).save(any(Gifticon.class));
    }

    @Test
    void 기프티콘_상태_수정_실패시_save_command가_수행되지_않는다() {
        final var gifticonId = 1L;
        final var userId = 1L;
        final var gifticon = new Gifticon(gifticonId, userId, "imageUrl", "name", "memo", LocalDate.now(), false, false, GifticonStore.CU);
        final var request = false;

        when(queryStorage.getByGifticonIdAndUserId(gifticonId, userId)).thenReturn(gifticon);

        assertThatThrownBy(() -> service.updateUsed(request, gifticonId, userId));
        verifyNoInteractions(commandStorage);
    }
}
