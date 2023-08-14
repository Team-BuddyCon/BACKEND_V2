package yapp.buddycon.web.gifticon.adapter.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;
import yapp.buddycon.common.request.PagingDTO;

@Getter
@Setter
public class SearchGifticonDTO extends PagingDTO {

  // gifticon
  private Boolean used;

  // store
  private Long storeId;
  private String storeName;

  // store category
  private Long storeCategoryId;
  private String storeCategoryName;

  // sort
  private SearchGifticonSortType sortType;
  private Direction sortDirection;

}
