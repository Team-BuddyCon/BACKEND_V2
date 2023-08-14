package yapp.buddycon.app.gifticon.adapter.request;

import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.common.request.PagingDTO;

@Getter
@Setter
public class SearchGifticonDTO extends PagingDTO {

  // gifticon
  private Boolean used;

  // store
  private Long storeId;

  // store category
  private Long storeCategoryId;

  // sort
  private SearchGifticonSortType sortType;

}
