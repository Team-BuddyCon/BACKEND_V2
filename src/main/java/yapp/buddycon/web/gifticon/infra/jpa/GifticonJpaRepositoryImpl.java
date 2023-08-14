package yapp.buddycon.web.gifticon.infra.jpa;

import static yapp.buddycon.web.gifticon.domain.QGifticon.gifticon;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonSortType;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;
import yapp.buddycon.web.gifticon.adapter.response.QGifticonResponseDTO;
import yapp.buddycon.web.gifticon.domain.Gifticon;
import yapp.buddycon.web.gifticon.domain.QStore;
import yapp.buddycon.web.gifticon.domain.QStoreCategory;

public class GifticonJpaRepositoryImpl extends QuerydslRepositorySupport implements
    GifticonJpaCustomRepository {

  private final JPAQueryFactory query;
  private QStore store;
  private QStoreCategory storeCategory;

  public GifticonJpaRepositoryImpl(EntityManager em) {
    super(Gifticon.class);
    query = new JPAQueryFactory(em);
    store = new QStore("store");
    storeCategory = new QStoreCategory("storeCategory");
  }

  @Override
  public Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable) {
    JPAQuery<GifticonResponseDTO> jpaQuery = query.select(
            new QGifticonResponseDTO(
                gifticon.id,
                gifticon.barcode,
                gifticon.imageUrl,
                gifticon.name,
                gifticon.memo,
                gifticon.expireDate,
                store.id,
                store.name,
                storeCategory.id,
                storeCategory.name
            )).from(gifticon)
        .leftJoin(gifticon.store, store)
        .leftJoin(store.storeCategory, storeCategory)
        .where(
            eqGifticonUsed(dto.getUsed()),
            eqStoreId(dto.getStoreId()),
            eqStoreCategoryId(dto.getStoreCategoryId())
        )
        .orderBy(orderByGifticonSortType(dto.getSortDirection(), dto.getSortType()));

    long totalCount = jpaQuery.fetchCount();
    List<GifticonResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanExpression eqGifticonUsed(Boolean used) {
    if (Objects.nonNull(used)) {
      return Boolean.TRUE.equals(used) ? gifticon.used.isTrue() : gifticon.used.isFalse();
    }
    return null;
  }

  private BooleanExpression eqStoreId(Long storeId) {
    return Objects.nonNull(storeId) ? store.id.eq(storeId) : null;
  }

  private BooleanExpression eqStoreCategoryId(Long storeCategoryId) {
    return Objects.nonNull(storeCategoryId) ? storeCategory.id.eq(storeCategoryId) : null;
  }

  private OrderSpecifier orderByGifticonSortType(Direction sortDirection, SearchGifticonSortType searchGifticonSortType) {
    if (SearchGifticonSortType.EXPIRE_DATE.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticon.expireDate.asc() : gifticon.expireDate.desc();
    }
    if (SearchGifticonSortType.CREATED_AT.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticon.createdAt.asc() : gifticon.createdAt.desc();
    }
    if (SearchGifticonSortType.NAME.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticon.name.asc() : gifticon.name.desc();
    }

    // default
    return gifticon.expireDate.desc();
  }

}
