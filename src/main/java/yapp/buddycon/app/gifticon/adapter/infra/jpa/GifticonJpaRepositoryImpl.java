package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import static yapp.buddycon.app.gifticon.adapter.infra.entity.QGifticonEntity.gifticonEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.QGifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;

public class GifticonJpaRepositoryImpl extends QuerydslRepositorySupport implements
    GifticonJpaCustomRepository {

  private final JPAQueryFactory query;

  public GifticonJpaRepositoryImpl(EntityManager em) {
    super(GifticonEntity.class);
    query = new JPAQueryFactory(em);
  }

  @Override
  public Slice<GifticonResponseDTO> findAllByUsedIsTrue(Pageable pageable) {
    List<GifticonResponseDTO> results = query.select(
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
            eqGifticonUsed(true)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageNumber() + 1).fetch();

    boolean hasNext = false;
    if (results.size() > pageable.getPageSize()) {
      hasNext = true;
      results.remove(pageable.getPageSize());
    }

    return new SliceImpl<>(results, pageable, hasNext);
  }

  private BooleanExpression eqGifticonUsed(boolean used) {
    return used ? gifticonEntity.used.isTrue() : gifticonEntity.used.isFalse();
  }

//  private OrderSpecifier orderByGifticonSortType(Direction sortDirection, SearchGifticonSortType searchGifticonSortType) {
//    if (SearchGifticonSortType.EXPIRE_DATE.equals(searchGifticonSortType)) {
//      return Direction.ASC.equals(sortDirection) ? gifticonEntity.expireDate.asc() : gifticonEntity.expireDate.desc();
//    }
//    if (SearchGifticonSortType.CREATED_AT.equals(searchGifticonSortType)) {
//      return Direction.ASC.equals(sortDirection) ? gifticonEntity.createdAt.asc() : gifticonEntity.createdAt.desc();
//    }
//    if (SearchGifticonSortType.NAME.equals(searchGifticonSortType)) {
//      return Direction.ASC.equals(sortDirection) ? gifticonEntity.name.asc() : gifticonEntity.name.desc();
//    }
//
//    // default
//    return gifticonEntity.expireDate.desc();
//  }

}
