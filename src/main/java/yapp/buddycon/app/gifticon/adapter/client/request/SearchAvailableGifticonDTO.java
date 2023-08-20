package yapp.buddycon.app.gifticon.adapter.client.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonStoreCategory;
import yapp.buddycon.common.request.PagingDTO;

@Getter
@Setter
public class SearchAvailableGifticonDTO extends PagingDTO {

  private GifticonStoreCategory gifticonStoreCategory;

  @NotNull
  private SearchGifticonSortType gifticonSortType = SearchGifticonSortType.EXPIRE_DATE;

}
