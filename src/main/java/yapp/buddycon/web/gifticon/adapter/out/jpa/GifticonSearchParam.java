package yapp.buddycon.web.gifticon.adapter.out.jpa;

import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.web.gifticon.adapter.in.request.SearchGifticonDTO;

@Getter
@Setter
public class GifticonSearchParam {

  private Boolean used;
  private Long brandId;

  public static GifticonSearchParam valueOf(SearchGifticonDTO dto) {
    GifticonSearchParam param = new GifticonSearchParam();
    param.setUsed(dto.getUsed());
    param.setBrandId(dto.getBrandId());
    return param;
  }

}
