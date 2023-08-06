package yapp.buddycon.web.gifticon.adapter.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GifticonVO {

  // gifticon
  private Long gifticonId;
  private String barcode;
  private String imageUrl;
  private String name;
  private String memo;
  private LocalDate expireDate;

  // store
  private Long storeId;
  private String storeName;

  // store category
  private Long storeCategoryId;
  private String storeCategoryName;

  @QueryProjection
  public GifticonVO(Long gifticonId, String barcode, String imageUrl, String name, String memo,
      LocalDate expireDate, Long storeId, String storeName, Long storeCategoryId,
      String storeCategoryName) {
    this.gifticonId = gifticonId;
    this.barcode = barcode;
    this.imageUrl = imageUrl;
    this.name = name;
    this.memo = memo;
    this.expireDate = expireDate;
    this.storeId = storeId;
    this.storeName = storeName;
    this.storeCategoryId = storeCategoryId;
    this.storeCategoryName = storeCategoryName;
  }
}
