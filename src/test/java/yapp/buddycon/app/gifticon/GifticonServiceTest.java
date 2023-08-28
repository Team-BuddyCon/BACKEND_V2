package yapp.buddycon.app.gifticon;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.app.gifticon.application.service.GifticonService;
import yapp.buddycon.common.request.PagingDTO;

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
      when(gifticonQueryStoragePort.findAllUnavailableGifticons(any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new GifticonResponseDTO(),
              new GifticonResponseDTO()))
      );

      // when
      Slice<GifticonResponseDTO> resultList = gifticonService.getUnavailableGifticons(requestDTO);

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
      when(gifticonQueryStoragePort.findAllAvailableGifticons(any(), any())).thenReturn(
          new SliceImpl<>(Arrays.asList(
              new GifticonResponseDTO(),
              new GifticonResponseDTO()))
      );

      // when
      Slice<GifticonResponseDTO> resultList = gifticonService.getAvailableGifticons(requestDTO);

      // then
      assertThat(resultList.getSize()).isEqualTo(2);
    }
  }

}
