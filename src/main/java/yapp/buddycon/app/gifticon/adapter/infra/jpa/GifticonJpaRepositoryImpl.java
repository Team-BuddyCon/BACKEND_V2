package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import static yapp.buddycon.app.gifticon.adapter.infra.entity.QGifticonEntity.gifticonEntity;

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
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;

public class GifticonJpaRepositoryImpl extends QuerydslRepositorySupport implements
    GifticonJpaCustomRepository {

  private final JPAQueryFactory query;

  public GifticonJpaRepositoryImpl(EntityManager em) {
    super(GifticonEntity.class);
    query = new JPAQueryFactory(em);
  }

  @Override
  public Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable) {
    JPAQuery<GifticonResponseDTO> jpaQuery = query.select(
            new QGifticonResponseDTO(
                gifticonEntity.id,
                gifticonEntity.barcode,
                gifticonEntity.imageUrl,
                gifticonEntity.name,
                gifticonEntity.memo,
                gifticonEntity.expireDate,
                gifticonEntity.gifticonStore,
                gifticonEntity.gifticonStoreCategory
            )).from(gifticonEntity)
        .where(
            eqGifticonUsed(dto.getUsed())
//            eqStoreId(dto.getStoreId()),
//            eqStoreCategoryId(dto.getStoreCategoryId())
        );
//        .orderBy(orderByGifticonSortType(dto.getSortDirection(), dto.getSortType()));

    long totalCount = jpaQuery.fetchCount();
    List<GifticonResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanExpression eqGifticonUsed(Boolean used) {
    if (Objects.nonNull(used)) {
      return Boolean.TRUE.equals(used) ? gifticonEntity.used.isTrue() : gifticonEntity.used.isFalse();
    }
    return null;
  }

//  private BooleanExpression eqStoreId(Long storeId) {
//    return Objects.nonNull(storeId) ? store.id.eq(storeId) : null;
//  }

//  private BooleanExpression eqStoreCategoryId(Long storeCategoryId) {
//    return Objects.nonNull(storeCategoryId) ? storeCategory.id.eq(storeCategoryId) : null;
//  }

  private OrderSpecifier orderByGifticonSortType(Direction sortDirection, SearchGifticonSortType searchGifticonSortType) {
    if (SearchGifticonSortType.EXPIRE_DATE.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticonEntity.expireDate.asc() : gifticonEntity.expireDate.desc();
    }
    if (SearchGifticonSortType.CREATED_AT.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticonEntity.createdAt.asc() : gifticonEntity.createdAt.desc();
    }
    if (SearchGifticonSortType.NAME.equals(searchGifticonSortType)) {
      return Direction.ASC.equals(sortDirection) ? gifticonEntity.name.asc() : gifticonEntity.name.desc();
    }

    // default
    return gifticonEntity.expireDate.desc();
  }

}
