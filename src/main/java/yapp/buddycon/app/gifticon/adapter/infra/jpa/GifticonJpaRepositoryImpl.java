package yapp.buddycon.app.gifticon.adapter.infra.jpa;

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
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.QGifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.Gifticon;
import yapp.buddycon.app.gifticon.domain.QGifticon;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.domain.QStore;
import yapp.buddycon.app.gifticon.domain.QStoreCategory;

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
                QGifticon.gifticon.id,
                QGifticon.gifticon.barcode,
                QGifticon.gifticon.imageUrl,
                QGifticon.gifticon.name,
                QGifticon.gifticon.memo,
                QGifticon.gifticon.expireDate,
                store.id,
                store.name,
                storeCategory.id,
                storeCategory.name
            )).from(QGifticon.gifticon)
        .leftJoin(QGifticon.gifticon.store, store)
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
      return Boolean.TRUE.equals(used) ? QGifticon.gifticon.used.isTrue() : QGifticon.gifticon.used.isFalse();
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
      return Direction.ASC.equals(sortDirection) ? QGifticon.gifticon.expireDate.asc() : QGifticon.gifticon.expireDate.desc();
    }
    if (SearchGifticonSortType.CREATED_AT.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? QGifticon.gifticon.createdAt.asc() : QGifticon.gifticon.createdAt.desc();
    }
    if (SearchGifticonSortType.NAME.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? QGifticon.gifticon.name.asc() : QGifticon.gifticon.name.desc();
    }

    // default
    return QGifticon.gifticon.expireDate.desc();
  }

}
