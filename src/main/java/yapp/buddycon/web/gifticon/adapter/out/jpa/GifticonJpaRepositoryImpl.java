package yapp.buddycon.web.gifticon.adapter.out.jpa;

import static yapp.buddycon.web.gifticon.domain.QGifticon.gifticon;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.adapter.in.response.QGifticonVO;
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
        .orderBy(gifticon.id.desc());

    long totalCount = jpaQuery.fetchCount();
    List<GifticonVO> results = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(results, pageable, totalCount);
  }

}
