package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import java.time.LocalDate;
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
    and g.gifticonStore = :gifticonStore
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndUserIdAndGifticonStore(
      long userId, GifticonStore gifticonStore, Pageable pageable);

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
    where g.used = false
    and g.userId = :userId
    and g.gifticonStoreCategory = :gifticonStoreCategory
    and g.gifticonStore = :gifticonStore
  """)
  Slice<GifticonResponseDTO> findAllByUsedIsFalseAndUserIdAndGifticonStoreCategoryAndGifticonStore(
      long userId, GifticonStoreCategory gifticonStoreCategory, GifticonStore gifticonStore, Pageable pageable);

  @Query(value = """
    select g
    from GifticonEntity g
    where g.id = :gifticonId
    and g.userId = :userId
  """)
  Optional<GifticonEntity> findByIdAndUserId(long gifticonId, long userId);

  Long countByUserIdAndUsed(Long userId, boolean used);

  @Modifying
  @Query(value = """
    update GifticonEntity g
    set g.deleted = true
    where g.id = :gifticonId
    and g.userId = :userId
  """)
  void delete(Long userId, Long gifticonId);

  boolean existsByUserIdAndId(long userId, long gifticonId);

  @Query(value = """
    select count(g)
    from GifticonEntity g
    where g.userId = :userId
    and (:used is null or g.used = :used)
    and (:gifticonStoreCategory is null or g.gifticonStoreCategory = :gifticonStoreCategory)
    and (:gifticonStore is null or g.gifticonStore = :gifticonStore)
    and (:toExpireDate is null or g.expireDate <= :toExpireDate)
  """)
  Long countByUserIdAndUsedAndGifticonStoreCategoryAndGifticonStoreAndExpireDate(
          long userId, Boolean used, GifticonStoreCategory gifticonStoreCategory, GifticonStore gifticonStore, LocalDate toExpireDate);

}
