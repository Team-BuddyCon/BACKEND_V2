package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.web.gifticon.adapter.in.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.port.out.GifticonQueryPort;
import yapp.buddycon.web.gifticon.application.service.GifticonService;

@ExtendWith(MockitoExtension.class)
public class GifticonServiceTest {

  @Mock
  private GifticonQueryPort gifticonQueryPort;
  @InjectMocks
  private GifticonService gifticonService;

  @Nested
  class getGifticons {

    private SearchGifticonDTO searchGifticonDTO = mock(SearchGifticonDTO.class);
    private int rowCount = 10;
    private int pageNumber = 1;

    @Test
    void 정상조회() {
      // given
      doReturn(rowCount).when(searchGifticonDTO).getRowCount();
      doReturn(pageNumber).when(searchGifticonDTO).getPageNumber();
      doReturn(new PageImpl<>(Arrays.asList(
          new GifticonVO(),
          new GifticonVO(),
          new GifticonVO()))
      ).when(gifticonQueryPort).findAll(any(), any(Pageable.class));

      // when
      Page<GifticonVO> resultList = gifticonService.getGifticons(searchGifticonDTO);

      // then
      assertThat(resultList.getSize()).isEqualTo(3);
    }

    @Test
    void 빈리스트() {
      // given
      doReturn(rowCount).when(searchGifticonDTO).getRowCount();
      doReturn(pageNumber).when(searchGifticonDTO).getPageNumber();
      doReturn(Page.empty())
          .when(gifticonQueryPort).findAll(any(), any(Pageable.class));

      // when
      Page<GifticonVO> resultList = gifticonService.getGifticons(searchGifticonDTO);

      // then
      assertThat(resultList.getTotalElements()).isEqualTo(0);
      assertThat(resultList.getContent().size()).isEqualTo(0);
    }
  }

}
