package yapp.buddycon.app.gifticon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStore;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GifticonJpaRepositoryTest {

  @Autowired
  private GifticonJpaRepository gifticonJpaRepository;

  @Nested
  class findAllByUsedIsTrue {

    @Test
    void 사용한_기프티콘_목록만_반환() {
      // given
      GifticonEntity 사용하지_않은_기프티콘 = createGifticonEntity("name1", false, GifticonStore.STARBUCKS);
      GifticonEntity 사용한_기프티콘 = createGifticonEntity("name2", true, GifticonStore.STARBUCKS);

      // when
      Slice<GifticonEntity> result = gifticonJpaRepository.findAllByUsedIsTrue(
          PageRequest.of(0, 10));

      // then
      assertThat(result.getContent().size()).isEqualTo(1);
      assertThat(result.getContent().get(0).getName()).isEqualTo(사용한_기프티콘.getName());
    }
  }

  // TODO Entity 메소드로 분리 예정
  private GifticonEntity createGifticonEntity(String name, boolean used, GifticonStore gifticonStore) {
    GifticonEntity gifticonEntity = GifticonEntity.builder()
        .barcode("aaaa")
        .imageUrl("url1")
        .name(name)
        .expireDate(LocalDate.now())
        .used(used)
        .gifticonStore(gifticonStore)
        .build();
    return gifticonJpaRepository.save(gifticonEntity);
  }

}
