package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonCountDao;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifticonCountDaoTest {

    @Mock
    GifticonJpaRepository repository;
    @InjectMocks
    GifticonCountDao dao;

    @Test
    void 사용완료_기프티콘_갯수조회_요청에_대해_해당하는_데이터를_반환한다() {
        final var userId = 1L;
        final var usedGifticonCount = 5L;
        when(repository.countByUserIdAndUsed(userId, true)).thenReturn(usedGifticonCount);

        Long actual = dao.countGifticons(userId, true);

        assertEquals(usedGifticonCount, actual);
    }

    @Test
    void 사용가능_기프티콘_갯수조회_요청에_대해_해당하는_데이터를_반환한다() {
        final var userId = 1L;
        final var usableGifticonCount = 10L;
        when(repository.countByUserIdAndUsed(userId, false)).thenReturn(usableGifticonCount);

        Long actual = dao.countGifticons(userId, false);

        assertEquals(usableGifticonCount, actual);
    }
}