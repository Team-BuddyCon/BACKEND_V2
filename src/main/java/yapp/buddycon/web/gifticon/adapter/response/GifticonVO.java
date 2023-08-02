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
  private String imageUrl;
  private String name;
  private LocalDate expireDate;

  @QueryProjection
  public GifticonVO(Long gifticonId, String imageUrl, String name, LocalDate expireDate) {
    this.gifticonId = gifticonId;
    this.imageUrl = imageUrl;
    this.name = name;
    this.expireDate = expireDate;
  }
}
