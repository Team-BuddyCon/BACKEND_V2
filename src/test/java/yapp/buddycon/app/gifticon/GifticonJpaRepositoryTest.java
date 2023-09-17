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
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStore;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.user.adapter.jpa.UserEntity;

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
      UserEntity user = new UserEntity(1l, 123l, "nickname", "dd@domain.com", "male", "20");
      GifticonEntity 사용하지_않은_기프티콘1 = createGifticonEntity("사용하지 않은 기프티콘 1", false, GifticonStore.STARBUCKS, user);
      GifticonEntity 사용하지_않은_기프티콘2 = createGifticonEntity("사용하지 않은 기프티콘 2", false, GifticonStore.STARBUCKS, user);
      GifticonEntity 사용하지_않은_기프티콘3 = createGifticonEntity("사용하지 않은 기프티콘 3", false, GifticonStore.STARBUCKS, user);
      GifticonEntity 사용한_기프티콘1 = createGifticonEntity("사용한 기프티콘 1", true, GifticonStore.STARBUCKS, user);
      GifticonEntity 사용한_기프티콘2 = createGifticonEntity("사용한 기프티콘 2", true, GifticonStore.STARBUCKS, user);

      // when
      Slice<GifticonResponseDTO> result = gifticonJpaRepository
          .findAllByUsedIsTrueAndUserId(1l, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(2);
      assertThat(result.getContent().get(0).getName()).isEqualTo(사용한_기프티콘1.getName());
    }

    @Test
    void 유저_필터링() {
      UserEntity user = new UserEntity(1l, 111l, "nickname1", "dd1@domain.com", "male", "20");
      GifticonEntity 사용한_기프티콘1 = createGifticonEntity("사용한 기프티콘 1", true, GifticonStore.STARBUCKS, user);

      UserEntity user2 = new UserEntity(2l, 222l, "nickname2", "dd2@domain.com", "male", "20");
      GifticonEntity 사용한_기프티콘2 = createGifticonEntity("사용한 기프티콘 2", true, GifticonStore.STARBUCKS, user2);

      // when
      Slice<GifticonResponseDTO> result = gifticonJpaRepository
          .findAllByUsedIsTrueAndUserId(1l, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(1);
      assertThat(result.getContent().get(0).getName()).isEqualTo(사용한_기프티콘1.getName());
    }
  }

  @Nested
  class findAllByUsedIsFalse {

    @Test
    void 사용_이전의_기프티콘_목록_조회() {
      // given
      UserEntity user = new UserEntity(1l, 123l, "nickname", "dd@domain.com", "male", "20");
      createGifticonEntity("name1", false, GifticonStore.STARBUCKS, user);
      createGifticonEntity("name2", false, GifticonStore.CU, user);
      createGifticonEntity("name3", true, GifticonStore.CU, user);

      // when
      Slice<GifticonResponseDTO> result = gifticonJpaRepository.findAllByUsedIsFalseAndUserId(
          1l, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void 이름_순서로_정렬_목록_조회() {
      // given
      UserEntity user = new UserEntity(1l, 123l, "nickname", "dd@domain.com", "male", "20");
      createGifticonEntity("name5", false, GifticonStore.STARBUCKS, user);
      GifticonEntity 조회대상_기프티콘 = createGifticonEntity("name1", false, GifticonStore.CU, user);
      createGifticonEntity("name4", false, GifticonStore.MACDONALD, user);

      // when
      Slice<GifticonResponseDTO> result = gifticonJpaRepository.findAllByUsedIsFalseAndUserId(
          1l, PageRequest.of(0, 10, SearchGifticonSortType.NAME.getSort()));

      // then
      assertThat(result.getContent()).hasSize(3);
      assertThat(result.getContent().get(0).getName()).isEqualTo(조회대상_기프티콘.getName());
    }
  }

  @Nested
  class findAllByUsedIsFalseAndGifticonStoreCategory {

    @Test
    void 카테고리_필터링_지정_목록_조회() {
      // given
      UserEntity user = new UserEntity(1l, 123l, "nickname", "dd@domain.com", "male", "20");
      createGifticonEntity("name1", false, GifticonStore.STARBUCKS, user);
      createGifticonEntity("name2", false, GifticonStore.CU, user);

      // when
      Slice<GifticonResponseDTO> result = gifticonJpaRepository
          .findAllByUsedIsFalseAndUserIdAndGifticonStoreCategory(1l, GifticonStoreCategory.CAFE, PageRequest.of(0, 10));

      // then
      assertThat(result.getContent()).hasSize(1);
    }
  }

  // TODO Entity 메소드로 분리 예정
  private GifticonEntity createGifticonEntity(String name, boolean used, GifticonStore gifticonStore, UserEntity userEntity) {
    GifticonEntity gifticonEntity = GifticonEntity.builder()
        .barcode("aaaa")
        .imageUrl("url1")
        .name(name)
        .expireDate(LocalDate.now())
        .used(used)
        .gifticonStore(gifticonStore)
        .user(userEntity)
        .build();
    return gifticonJpaRepository.save(gifticonEntity);
  }

}
