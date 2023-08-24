package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

public interface GifticonJpaRepository extends JpaRepository<GifticonEntity, Long> {

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.barcode, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = true
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsTrue(Pageable pageable);

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.barcode, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = false
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalse(Pageable pageable);

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.barcode, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = false
    and g.gifticonStoreCategory = :gifticonStoreCategory
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndGifticonStoreCategory(
      GifticonStoreCategory gifticonStoreCategory, Pageable pageable);

}