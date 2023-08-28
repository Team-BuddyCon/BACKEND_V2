package yapp.buddycon.app.gifticon.adapter.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
  @Column(name = "barcode", nullable = false)
  private String barcode;
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
  private GifticonEntity(String barcode, String imageUrl, String name, String memo,
      LocalDate expireDate, boolean used, GifticonStore gifticonStore) {
    this.barcode = barcode;
    this.imageUrl = imageUrl;
    this.name = name;
    this.memo = memo;
    this.expireDate = expireDate;
    this.used = used;
    this.gifticonStore = gifticonStore;
    this.gifticonStoreCategory = gifticonStore.getCategory();
  }

}
