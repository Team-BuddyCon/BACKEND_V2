package yapp.buddycon.web.gifticon.adapter.in.response;

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

  // brand
  private Long brandId;
  private String brandName;

}
