package yapp.buddycon.app.gifticon.adapter.infra.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;
import yapp.buddycon.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "gifticon")
public class GifticonEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gifticon_id")
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "image_url", nullable = false)
  private String imageUrl;
  @Column(name = "name", nullable = false, length = 16)
  private String name;
  @Column(name = "memo", length = 20)
  private String memo;
  @Column(name = "expire_date", nullable = false)
  private LocalDate expireDate;
  private boolean used;
  @Enumerated(EnumType.STRING)
  private GifticonStore gifticonStore;
  @Enumerated(EnumType.STRING)
  private GifticonStoreCategory gifticonStoreCategory;

  @Builder
  public GifticonEntity(Long id, Long userId, String imageUrl, String name, String memo,
      LocalDate expireDate, boolean used, GifticonStore gifticonStore) {
    this.id = id;
    this.userId = userId;
    this.imageUrl = imageUrl;
    this.name = name;
    this.memo = memo;
    this.expireDate = expireDate;
    this.used = used;
    this.gifticonStore = gifticonStore;
    this.gifticonStoreCategory = gifticonStore.getCategory();
  }

}
