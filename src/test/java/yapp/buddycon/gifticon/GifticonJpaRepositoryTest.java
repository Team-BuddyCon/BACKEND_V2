package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import yapp.buddycon.web.gifticon.adapter.in.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.adapter.out.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.adapter.out.jpa.GifticonSearchParam;
import yapp.buddycon.web.gifticon.domain.Gifticon;

@DataJpaTest
public class GifticonJpaRepositoryTest {

  @Autowired
  private GifticonJpaRepository gifticonJpaRepository;

  @Nested
  class findAll {

    @Test
    public void 정상조회() {
      // given
      final Gifticon gifticon1 = Gifticon.builder()
          .barcode("aaaa")
          .imageUrl("url1")
          .name("name1")
          .expireDate(LocalDate.now())
          .used(false)
          .build();
      final Gifticon gifticon2 = Gifticon.builder()
          .barcode("bbbb")
          .imageUrl("url2")
          .name("name2")
          .expireDate(LocalDate.now())
          .used(false)
          .build();

      gifticonJpaRepository.save(gifticon1);
      gifticonJpaRepository.save(gifticon2);

      // when
      Page<GifticonVO> result = gifticonJpaRepository.findAll(
          GifticonSearchParam.valueOf(new SearchGifticonDTO()), PageRequest.of(1, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void 빈리스트() {
      // given

      // when
      Page<GifticonVO> result = gifticonJpaRepository.findAll(
          GifticonSearchParam.valueOf(new SearchGifticonDTO()), PageRequest.of(1, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(0);
    }
  }

}
