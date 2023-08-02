package yapp.buddycon.web.gifticon.infra.jpa;

import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;

@Getter
@Setter
public class GifticonSearchParam {

  private Boolean used;

  public static GifticonSearchParam valueOf(SearchGifticonDTO dto) {
    GifticonSearchParam param = new GifticonSearchParam();
    param.setUsed(dto.getUsed());
    return param;
  }

}
