package yapp.buddycon.web.gifticon.adapter.request;

import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.common.request.PagingDTO;

@Getter
@Setter
public class SearchGifticonDTO extends PagingDTO {

  private Boolean used;
}
