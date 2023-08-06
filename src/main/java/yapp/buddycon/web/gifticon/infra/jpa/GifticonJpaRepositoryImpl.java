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
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;
import yapp.buddycon.common.request.OrderByType;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonSortType;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.adapter.response.QGifticonVO;
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
  public Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable) {
    JPAQuery<GifticonVO> jpaQuery = query.select(
            new QGifticonVO(
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

    long totalCount = jpaQuery.fetchCount();
    List<GifticonVO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanBuilder createWhereCondition(GifticonSearchParam param) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (Boolean.TRUE.equals(param.getUsed())) {
      booleanBuilder.and(gifticon.used.isTrue());
    } else if (Boolean.FALSE.equals(param.getUsed())) {
      booleanBuilder.and(gifticon.used.isFalse());
    }
    return booleanBuilder;
  }

}
