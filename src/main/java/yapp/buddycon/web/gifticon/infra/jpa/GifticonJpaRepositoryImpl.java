package yapp.buddycon.web.gifticon.infra.jpa;

import static yapp.buddycon.web.gifticon.domain.QGifticon.gifticon;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.util.StringUtils;
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
  public Page<GifticonResponseDTO> findAll(GifticonSearchParam param, Pageable pageable) {
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
        .where(createWhereCondition(param));

    jpaQuery = addOrderByQuery(jpaQuery, param);

    long totalCount = jpaQuery.fetchCount();
    List<GifticonResponseDTO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanBuilder createWhereCondition(GifticonSearchParam param) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if (Boolean.TRUE.equals(param.getUsed())) {
      booleanBuilder.and(gifticon.used.isTrue());
    } else if (Boolean.FALSE.equals(param.getUsed())) {
      booleanBuilder.and(gifticon.used.isFalse());
    }
    if (Objects.nonNull(param.getStoreId())) {
      booleanBuilder.and(store.id.eq(param.getStoreId()));
    }
    if (StringUtils.hasText(param.getStoreName())) {
      booleanBuilder.and(store.name.eq(param.getStoreName()));
    }
    if (Objects.nonNull(param.getStoreCategoryId())) {
      booleanBuilder.and(storeCategory.id.eq(param.getStoreCategoryId()));
    }
    if (StringUtils.hasText(param.getStoreCategoryName())) {
      booleanBuilder.and(storeCategory.name.eq(param.getStoreCategoryName()));
    }

    return booleanBuilder;
  }

  // TODO 추상화 후 분리?
  private <T> JPAQuery<T> addOrderByQuery(JPAQuery<T> query, GifticonSearchParam param) {
    if (Objects.nonNull(param.getSortType())) {
      if (Direction.ASC.equals(param.getSortDirection())) {
        if (SearchGifticonSortType.EXPIRE_DATE.equals(param.getSortType())) {
          query.orderBy(gifticon.expireDate.asc());
        }
        if (SearchGifticonSortType.CREATED_AT.equals(param.getSortType())) {
          query.orderBy(gifticon.createdAt.asc());
        }
        if (SearchGifticonSortType.NAME.equals(param.getSortType())) {
          query.orderBy(gifticon.name.asc());
        }
      } else {
        if (SearchGifticonSortType.EXPIRE_DATE.equals(param.getSortType())) {
          query.orderBy(gifticon.expireDate.desc());
        }
        if (SearchGifticonSortType.CREATED_AT.equals(param.getSortType())) {
          query.orderBy(gifticon.createdAt.desc());
        }
        if (SearchGifticonSortType.NAME.equals(param.getSortType())) {
          query.orderBy(gifticon.name.desc());
        }
      }
    }
    return query;
  }

}
