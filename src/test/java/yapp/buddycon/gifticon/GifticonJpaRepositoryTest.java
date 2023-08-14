package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonSortType;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;
import yapp.buddycon.web.gifticon.domain.Store;
import yapp.buddycon.web.gifticon.domain.StoreCategory;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.domain.Gifticon;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GifticonJpaRepositoryTest {

  @Autowired
  private GifticonJpaRepository gifticonJpaRepository;
  @Autowired
  private JpaRepository jpaRepository;

  @Nested
  class findAll {

    @Test
    void 일반목록조회() {
      // given
      createGifticon("name1", false, null);
      createGifticon("name2", true, null);

      // when
      Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(
          new SearchGifticonDTO(), PageRequest.of(0, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(2);
    }

    @Test
    void 빈목록조회() {
      // given

      // when
      Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(new SearchGifticonDTO(), PageRequest.of(1, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    void 정렬조회_이름() {
      // given
      createGifticon("name1", true, null);
      Gifticon 조회대상 = createGifticon("name3", true, null);
      createGifticon("name2", true, null);

      // when
      SearchGifticonDTO dto = new SearchGifticonDTO();
      dto.setSortType(SearchGifticonSortType.NAME);
      Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(dto, PageRequest.of(0, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(3);
      assertThat(result.getContent().get(0).getName()).isEqualTo(조회대상.getName());
    }

    @Nested
    class 필터링조회 {
      private StoreCategory 카테고리1, 카테고리2;
      private Store 카테고리1_가맹점1, 카테고리1_가멍잼2, 카테고리2_가맹점3;
      private Gifticon 가맹점_없는_기프티콘1, 카테고리1_가맹점1_기프티콘2, 카테고리1_가맹점2_기프티콘3, 카테고리2_가맹점3_기프티콘4, 카테고리2_가맹점3_기프티콘5_미사용;

      @BeforeEach
      void initData() {
        가맹점_없는_기프티콘1 = createGifticon("gifticon1", true, null);

        카테고리1 = createStoreCategory("category1");
        카테고리1_가맹점1 = createStore("store1", 카테고리1);
        카테고리1_가맹점1_기프티콘2 = createGifticon("gifticon2", true, 카테고리1_가맹점1);
        카테고리1_가멍잼2 = createStore("store2", 카테고리1);
        카테고리1_가맹점2_기프티콘3 = createGifticon("gifticon3", true, 카테고리1_가멍잼2);

        카테고리2 = createStoreCategory("category2");
        카테고리2_가맹점3 = createStore("store3", 카테고리2);
        카테고리2_가맹점3_기프티콘4 = createGifticon("gifticon4", true, 카테고리2_가맹점3);
        카테고리2_가맹점3_기프티콘5_미사용 = createGifticon("gifticon4", false, 카테고리2_가맹점3);
      }

      @Test
      void 필터링없음() {
        // given
        SearchGifticonDTO dto = new SearchGifticonDTO();

        // when
        Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(dto, PageRequest.of(0, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(5);
      }

      @Test
      void 사용여부_필터링() {
        // given
        SearchGifticonDTO dto = new SearchGifticonDTO();
        dto.setUsed(false);

        // when
        Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(dto, PageRequest.of(0, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
      }

      @Test
      void 카테고리_가맹점_필터링() {
        // given
        SearchGifticonDTO dto = new SearchGifticonDTO();
        dto.setStoreCategoryId(카테고리1.getId());
        dto.setStoreId(카테고리1_가멍잼2.getId());

        // when
        Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(dto, PageRequest.of(0, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getGifticonId()).isEqualTo(카테고리1_가맹점2_기프티콘3.getId());
      }
    }

  }

  // TODO Entity 메소드로 분리 예정
  /** Create Start **/
  private Gifticon createGifticon(String name, boolean used, Store store) {
    Gifticon gifticon = Gifticon.builder()
        .barcode("aaaa")
        .imageUrl("url1")
        .name(name)
        .expireDate(LocalDate.now())
        .used(used)
        .store(store)
        .build();
    return gifticonJpaRepository.save(gifticon);
  }

  private Store createStore(String name, StoreCategory storeCategory) {
    Store store = Store.builder()
        .name(name)
        .storeCategory(storeCategory)
        .build();

    jpaRepository.save(store);
    return store;
  }

  private StoreCategory createStoreCategory(String name) {
    StoreCategory storeCategory = StoreCategory.builder()
        .name(name)
        .build();
    jpaRepository.save(storeCategory);
    return storeCategory;
  }
  /** Create End **/

}
