package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonSearchParam;
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
          .used(true)
          .build();

      gifticonJpaRepository.save(gifticon1);
      gifticonJpaRepository.save(gifticon2);

      // when
      SearchGifticonDTO dto1 = new SearchGifticonDTO();
      Page<GifticonVO> result1 = gifticonJpaRepository.findAll(
          GifticonSearchParam.valueOf(dto1), PageRequest.of(0, 10));

      SearchGifticonDTO dto2 = new SearchGifticonDTO();
      dto2.setUsed(false);
      Page<GifticonVO> result2 = gifticonJpaRepository.findAll(
          GifticonSearchParam.valueOf(dto2), PageRequest.of(0, 10));

      // then
      assertThat(result1.getTotalElements()).isEqualTo(2);

      assertThat(result2.getTotalElements()).isEqualTo(1);
      assertThat(result2.getContent().get(0).getName()).isEqualTo("name1");
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
