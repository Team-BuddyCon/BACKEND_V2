package yapp.buddycon.app.gifticon.adapter.client.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStore;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;

@Getter
@NoArgsConstructor
public class GifticonResponseDTO {

  private Long gifticonId;
  private String barcode;
  private String imageUrl;
  private String name;
  private String memo;
  private LocalDate expireDate;
  private GifticonStore gifticonStore;
  private GifticonStoreCategory gifticonStoreCategory;

  public GifticonResponseDTO(Long gifticonId, String barcode, String imageUrl, String name,
      String memo, LocalDate expireDate, GifticonStore gifticonStore,
      GifticonStoreCategory gifticonStoreCategory) {
    this.gifticonId = gifticonId;
    this.barcode = barcode;
    this.imageUrl = imageUrl;
    this.name = name;
    this.memo = memo;
    this.expireDate = expireDate;
    this.gifticonStore = gifticonStore;
    this.gifticonStoreCategory = gifticonStoreCategory;
  }
}
