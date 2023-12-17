package yapp.buddycon.app.gifticon;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.adapter.GifticonException.GifticonExceptionCode;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.application.service.GifticonService;
import yapp.buddycon.app.common.request.PagingDTO;

@ExtendWith(MockitoExtension.class)
public class GifticonServiceTest {

  @Mock
  private GifticonQueryStorage gifticonQueryStoragePort;
  @InjectMocks
  private GifticonService gifticonService;

  @Nested
  class getUnavailableGifticons {

    @Test
    void 정상조회() {
      // given
      PagingDTO requestDTO = new PagingDTO();
      when(gifticonQueryStoragePort.findAllUnavailableGifticons(anyLong(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new GifticonResponseDTO(1L, "", "", "", null, null, null),
              new GifticonResponseDTO(2L, "", "", "", null, null, null)))
      );

      // when
      Slice<GifticonResponseDTO> resultList = gifticonService.getUnavailableGifticons(1l, requestDTO);

      // then
      assertThat(resultList.getSize()).isEqualTo(2);
    }
  }

  @Nested
  class getAvailableGifticons {

    @Test
    void 정상조회() {
      // given
      SearchAvailableGifticonDTO requestDTO = new SearchAvailableGifticonDTO();
      when(gifticonQueryStoragePort.findAllAvailableGifticons(anyLong(), any(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new GifticonResponseDTO(1L, "", "", "", null, null, null),
              new GifticonResponseDTO(2L, "", "", "", null, null, null)))
      );

      // when
      Slice<GifticonResponseDTO> resultList = gifticonService.getAvailableGifticons(1l, requestDTO);

      // then
      assertThat(resultList.getSize()).isEqualTo(2);
    }
  }

  @Nested
  class getGifticon {

    private final Long REQUEST_USER_ID = 1l;
    private final Long REQUEST_GIFTICON_ID = 1000l;

    @Test
    void 요청한_기프티콘이_있을_경우_DTO를_반환한다() {
      // given
      GifticonResponseDTO gifticonResponseDTO = new GifticonResponseDTO(REQUEST_GIFTICON_ID, "", "", "", null, null, null);
      when(gifticonQueryStoragePort.findByGifticonIdAndUserId(REQUEST_GIFTICON_ID, REQUEST_USER_ID)).thenReturn(gifticonResponseDTO);

      // when
      GifticonResponseDTO result = gifticonService.getGifticon(REQUEST_USER_ID, REQUEST_GIFTICON_ID);

      // then
      assertThat(result.gifticonId()).isEqualTo(REQUEST_GIFTICON_ID);
    }

    @Test
    void 요청한_기프티콘을_찾을_수_없을시_Exception을_던진다() {
      // given
      doThrow(new GifticonException(GifticonExceptionCode.GIFTICON_NOT_FOUND)).when(gifticonQueryStoragePort).findByGifticonIdAndUserId(REQUEST_GIFTICON_ID, REQUEST_USER_ID);

      // when
      Throwable exception = assertThrows(GifticonException.class, () -> {
        gifticonService.getGifticon(REQUEST_USER_ID, REQUEST_GIFTICON_ID);
      });

      // then
      assertEquals(GifticonExceptionCode.GIFTICON_NOT_FOUND.getMessage(), exception.getMessage());
    }
  }
}