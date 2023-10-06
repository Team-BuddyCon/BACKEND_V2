package yapp.buddycon.app.gifticon.adapter.client.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.gifticon.domain.GifticonStore;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

@Getter
@NoArgsConstructor
public class GifticonResponseDTO {

  private Long gifticonId;
  private String imageUrl;
  private String name;
  private String memo;
  private LocalDate expireDate;
  private GifticonStore gifticonStore;
  private GifticonStoreCategory gifticonStoreCategory;

  public GifticonResponseDTO(Long gifticonId, String imageUrl, String name,
      String memo, LocalDate expireDate, GifticonStore gifticonStore,
      GifticonStoreCategory gifticonStoreCategory) {
    this.gifticonId = gifticonId;
    this.imageUrl = imageUrl;
    this.name = name;
    this.memo = memo;
    this.expireDate = expireDate;
    this.gifticonStore = gifticonStore;
    this.gifticonStoreCategory = gifticonStoreCategory;
  }
}
