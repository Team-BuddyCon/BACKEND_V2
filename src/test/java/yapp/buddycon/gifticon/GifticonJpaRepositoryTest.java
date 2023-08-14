package yapp.buddycon.gifticon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
    void 정상조회() {
      // given
      Gifticon gifticon1 = createGifticon("name1", false, null);
      Gifticon gifticon2 = createGifticon("name2", true, null);

      // when
      SearchGifticonDTO dto1 = new SearchGifticonDTO();
      Page<GifticonResponseDTO> result1 = gifticonJpaRepository.findAll(dto1, PageRequest.of(0, 10));

      SearchGifticonDTO dto2 = new SearchGifticonDTO();
      dto2.setUsed(false);
      Page<GifticonResponseDTO> result2 = gifticonJpaRepository.findAll(dto2, PageRequest.of(0, 10));

      // then
      assertThat(result1.getTotalElements()).isEqualTo(2);

      assertThat(result2.getTotalElements()).isEqualTo(1);
      assertThat(result2.getContent().get(0).getName()).isEqualTo("name1");
    }

    @Test
    void 빈리스트() {
      // given

      // when
      Page<GifticonResponseDTO> result = gifticonJpaRepository.findAll(new SearchGifticonDTO(), PageRequest.of(1, 10));

      // then
      assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    void 정렬조회_이름() {
      // given
      Gifticon gifticon1 = createGifticon("name1", true, null);
      Gifticon gifticon3 = createGifticon("name3", true, null);
      Gifticon gifticon2 = createGifticon("name2", true, null);

      // when
      SearchGifticonDTO dto1 = new SearchGifticonDTO();
      dto1.setSortType(SearchGifticonSortType.NAME);
      dto1.setSortDirection(Direction.ASC);
      Page<GifticonResponseDTO> result1 = gifticonJpaRepository.findAll(dto1, PageRequest.of(0, 10));

      SearchGifticonDTO dto2 = new SearchGifticonDTO();
      dto2.setSortType(SearchGifticonSortType.NAME);
      Page<GifticonResponseDTO> result2 = gifticonJpaRepository.findAll(dto2, PageRequest.of(0, 10));

      // then
      assertThat(result1.getTotalElements()).isEqualTo(3);
      assertThat(result1.getContent().get(0).getName()).isEqualTo(gifticon1.getName());

      assertThat(result2.getTotalElements()).isEqualTo(3);
      assertThat(result2.getContent().get(0).getName()).isEqualTo(gifticon3.getName());
    }

    @Test
    void 필터링조회() {
      // given
      Gifticon gifticon1 = createGifticon("gifticon1", true, null);

      StoreCategory storeCategory1 = createStoreCategory("category1");
      Store store1 = createStore(1l, "store1", storeCategory1);
      Gifticon gifticon2 = createGifticon("gifticon2", true, store1);
      Store store2 = createStore(2l, "store2", storeCategory1);
      Gifticon gifticon3 = createGifticon("gifticon3", true, store2);

      StoreCategory storeCategory2 = createStoreCategory("category2");
      Store store3 = createStore(3l, "store3", storeCategory2);
      Gifticon gifticon4 = createGifticon("gifticon4", true, store3);
      Gifticon gifticon5 = createGifticon("gifticon4", false, store3);

      // when
      SearchGifticonDTO dto1 = new SearchGifticonDTO();
      Page<GifticonResponseDTO> result1 = gifticonJpaRepository.findAll(dto1, PageRequest.of(0, 10));

      SearchGifticonDTO dto2 = new SearchGifticonDTO();
      dto2.setStoreId(2l);
      Page<GifticonResponseDTO> result2 = gifticonJpaRepository.findAll(dto2, PageRequest.of(0, 10));

      SearchGifticonDTO dto3 = new SearchGifticonDTO();
      dto3.setStoreCategoryId(storeCategory2.getId());
      Page<GifticonResponseDTO> result3 = gifticonJpaRepository.findAll(dto3, PageRequest.of(0, 10));

      // then
      assertThat(result1.getTotalElements()).isEqualTo(5);

      assertThat(result2.getTotalElements()).isEqualTo(1);
      assertThat(result2.getContent().get(0).getStoreName()).isEqualTo(store2.getName());

      assertThat(result3.getTotalElements()).isEqualTo(2);
      assertThat(result3.getContent().get(0).getStoreCategoryName()).isEqualTo(storeCategory2.getName());
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

    private Store createStore(Long id, String name, StoreCategory storeCategory) {
      Store store = Store.builder()
          .id(id)
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

}
