package yapp.buddycon.app.gifticon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCountDto;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.application.service.GifticonCountService;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifticonCountServiceTest {

    @Mock
    GifticonQueryStorage queryStorage;
    @InjectMocks
    GifticonCountService service;

    @Test
    void 전달받은_값으로_queryStorage_메소드를_호출한다() {
        // given
        long userId = 1l;
        GifticonCountDto dto = new GifticonCountDto(true, GifticonStoreCategory.CAFE, GifticonStore.GS25, null);

        when(queryStorage.countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
                anyLong(), any(), any(), any(), any())).thenReturn(10l);

        // when
        service.countGifticons(userId, dto);

        // then
        ArgumentCaptor<Long> userIdArgument = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<GifticonStoreCategory> gifticonStoreCategoryArgument = ArgumentCaptor.forClass(GifticonStoreCategory.class);
        ArgumentCaptor<GifticonStore> gifticonStoreArgument = ArgumentCaptor.forClass(GifticonStore.class);
        ArgumentCaptor<Boolean> usedArgument = ArgumentCaptor.forClass(Boolean.class);

        verify(queryStorage).countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
                userIdArgument.capture(), usedArgument.capture(), gifticonStoreCategoryArgument.capture(), gifticonStoreArgument.capture(), any());
        assertEquals(userId, userIdArgument.getValue());
        assertEquals(dto.gifticonStoreCategory(), gifticonStoreCategoryArgument.getValue());
        assertEquals(dto.gifticonStore(), gifticonStoreArgument.getValue());
        assertEquals(dto.used(), usedArgument.getValue());
    }

    @Test
    void queryStorage가_반환한_값을_그대로_반환한다() {
        // given
        long expectedResult = 10l;
        GifticonCountDto dto = new GifticonCountDto(null, null, null, null);

        when(queryStorage.countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
                anyLong(), any(), any(), any(), any())).thenReturn(expectedResult);

        // when
        long result = service.countGifticons(1l, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void 전달받은_값에_remainingDays가_null이_아닌_경우_날짜로_변환해서_queryStorage_메소드를_호출한다() {
        // given
        GifticonCountDto dto = new GifticonCountDto(null, null, null, 30);

        when(queryStorage.countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
                anyLong(), any(), any(), any(), any())).thenReturn(10l);

        // when
        service.countGifticons(1l, dto);

        // then
        ArgumentCaptor<LocalDate> toExpireDateArgument = ArgumentCaptor.forClass(LocalDate.class);

        verify(queryStorage).countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
                anyLong(), any(), any(), any(), toExpireDateArgument.capture());
        assertEquals(LocalDate.now().plusDays(30), toExpireDateArgument.getValue());
    }

}