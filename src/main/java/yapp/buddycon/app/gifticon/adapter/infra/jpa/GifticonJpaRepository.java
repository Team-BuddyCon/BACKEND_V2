package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import java.util.Optional;

public interface GifticonJpaRepository extends JpaRepository<GifticonEntity, Long> {

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = true
    and g.userId = :userId
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsTrueAndUserId(long userId, Pageable pageable);

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = false
    and g.userId = :userId
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndUserId(long userId, Pageable pageable);

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.used = false
    and g.userId = :userId
    and g.gifticonStoreCategory = :gifticonStoreCategory
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndUserIdAndGifticonStoreCategory(
      long userId, GifticonStoreCategory gifticonStoreCategory, Pageable pageable);

  @Query(value = """
    select new yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO
      (g.id, g.imageUrl, g.name, g.memo, g.expireDate, g.gifticonStore, g.gifticonStoreCategory)
    from GifticonEntity g
    where g.id = :gifticonId
    and g.userId = :userId
  """)
  Optional<GifticonResponseDTO> findByIdAndUserId(long gifticonId, long userId);

  @Modifying
  @Query(value = """
    update GifticonEntity g
    set g.deleted = true
    where g.id = :gifticonId
    and g.userId = :userId
  """)
  void delete(Long userId, Long gifticonId);

  boolean existsByUserIdAndId(long userId, long gifticonId);
}
