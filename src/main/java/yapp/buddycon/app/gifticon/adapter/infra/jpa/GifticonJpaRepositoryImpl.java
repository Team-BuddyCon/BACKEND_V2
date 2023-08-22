package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import static yapp.buddycon.app.gifticon.adapter.infra.entity.QGifticonEntity.gifticonEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonSortType;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.QGifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

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
            gifticonEntity.used.isTrue()
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize() + 1).fetch();

    return checkLastPage(pageable, results);
  }

  @Override
  public Slice<GifticonResponseDTO> findAllAvailableGifticons(LocalDate today,
      GifticonStoreCategory gifticonStoreCategory, SearchGifticonSortType searchGifticonSortType,
      Pageable pageable) {
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
            gifticonEntity.used.isFalse(),
            gifticonEntity.expireDate.before(today.plusDays(1l)),
            eqGifticonStoreCategory(gifticonStoreCategory)
        )
        .orderBy(searchGifticonSortType.getOrderByQuery())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize() + 1).fetch();

    return checkLastPage(pageable, results);
  }

  private BooleanExpression eqGifticonStoreCategory(GifticonStoreCategory gifticonStoreCategory) {
    return Objects.nonNull(gifticonStoreCategory) ?
        gifticonEntity.gifticonStoreCategory.eq(gifticonStoreCategory) : null;
  }

  private <T> Slice<T> checkLastPage(Pageable pageable, List<T> results) {
    boolean hasNext = false;
    if (results.size() > pageable.getPageSize()) {
      hasNext = true;
      results.remove(pageable.getPageSize());
    }
    return new SliceImpl<>(results, pageable, hasNext);
  }

}
