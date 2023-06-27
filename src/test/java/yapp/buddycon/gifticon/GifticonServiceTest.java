package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import org.springframework.data.domain.PageImpl;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.service.GifticonService;

@ExtendWith(MockitoExtension.class)
public class GifticonServiceTest {

  @InjectMocks
  private GifticonQueryPort gifticonQueryPort;
  @Mock
  private GifticonService gifticonService;

  @Nested
  class getGifticons {

    @Test
    void 정상조회() {
      // given
      doReturn(new PageImpl<>(Arrays.asList(
          new GifticonVO(),
          new GifticonVO(),
          new GifticonVO()))
      ).when(gifticonQueryPort).findAll(any(), any(Pageable.class));

      // when
      Page<GifticonVO> resultList = gifticonService.getGifticons(any());

      // then
      assertThat(resultList.getSize()).isEqualTo(3);
    }

    @Test
    void 빈리스트() {
      // given
      doReturn(Page.empty())
          .when(gifticonQueryPort).findAll(any(), any(Pageable.class));

      // when
      Page<GifticonVO> resultList = gifticonService.getGifticons(any());

      // then
      assertThat(resultList.getTotalElements()).isEqualTo(0);
      assertThat(resultList.getContent().size()).isEqualTo(0);
    }
  }

}
