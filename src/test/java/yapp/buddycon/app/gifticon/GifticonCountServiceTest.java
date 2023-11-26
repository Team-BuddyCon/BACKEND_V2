package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.gifticon.application.port.in.GifticonCountUsecase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.application.service.GifticonCountService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifticonCountServiceTest {

    @Mock
    GifticonQueryStorage queryStorage;
    @InjectMocks
    GifticonCountService service;

    @Test
    void 사용완료_기프티콘_갯수조회_요청에_대해_해당하는_데이터를_반환한다() {
        final var userId = 1L;
        final var usedGifticonCount = 5L;
        when(queryStorage.countByUserIdAndUsed(userId, true)).thenReturn(usedGifticonCount);

        Long actual = service.countGifticons(userId, true);

        assertEquals(usedGifticonCount, actual);
    }

    @Test
    void 사용가능_기프티콘_갯수조회_요청에_대해_해당하는_데이터를_반환한다() {
        final var userId = 1L;
        final var usableGifticonCount = 10L;
        when(queryStorage.countByUserIdAndUsed(userId, false)).thenReturn(usableGifticonCount);

        Long actual = service.countGifticons(userId, false);

        assertEquals(usableGifticonCount, actual);
    }
}