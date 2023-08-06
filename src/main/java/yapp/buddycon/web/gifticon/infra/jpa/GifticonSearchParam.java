package yapp.buddycon.web.gifticon.infra.jpa;

import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.common.request.OrderByType;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonSortType;

@Getter
@Setter
public class GifticonSearchParam {

  // gifticon
  private Boolean used;

  // store
  private Long storeId;
  private String storeName;

  // store category
  private Long storeCategoryId;
  private String storeCategoryName;

  // DefaultValue
  private SearchGifticonSortType sortType;
  private OrderByType orderByType;

  public static GifticonSearchParam valueOf(SearchGifticonDTO dto) {
    GifticonSearchParam param = new GifticonSearchParam();
    param.setUsed(dto.getUsed());
    param.setStoreId(dto.getStoreId());
    param.setStoreName(dto.getStoreName());
    param.setStoreCategoryId(dto.getStoreCategoryId());
    param.setStoreCategoryName(dto.getStoreCategoryName());
    param.setSortType(dto.getSortType());
    param.setOrderByType(dto.getOrderByType());
    return param;
  }

}
