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
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.adapter.response.QGifticonVO;
import yapp.buddycon.web.gifticon.domain.Gifticon;
import yapp.buddycon.web.gifticon.domain.QBrand;

public class GifticonJpaRepositoryImpl extends QuerydslRepositorySupport implements
    GifticonJpaCustomRepository {

  private final JPAQueryFactory query;
  private QBrand brand;

  public GifticonJpaRepositoryImpl(EntityManager em) {
    super(Gifticon.class);
    query = new JPAQueryFactory(em);
    brand = new QBrand("brand");
  }

  @Override
  public Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable) {
    JPAQuery<GifticonVO> jpaQuery = query.select(
            new QGifticonVO(
                gifticon.id,
                gifticon.imageUrl,
                gifticon.name,
                gifticon.expireDate,
                brand.id,
                brand.name
            )).from(gifticon)
        .leftJoin(gifticon.brand, brand)
        .where(createWhereCondition(param))
        .orderBy(gifticon.id.desc());

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
    if (Objects.nonNull(param.getBrandId())) {
      booleanBuilder.and(brand.id.eq(param.getBrandId()));
    }
    return booleanBuilder;
  }

}
