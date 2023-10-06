package yapp.buddycon.app.gifticon.adapter.client.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;
import yapp.buddycon.common.request.PagingDTO;

@Getter
@Setter
public class SearchAvailableGifticonDTO extends PagingDTO {

  private GifticonStoreCategory gifticonStoreCategory;

  private SearchGifticonSortType gifticonSortType = SearchGifticonSortType.EXPIRE_DATE;

  @Override
  public Pageable toPageable() {
    return PageRequest.of(super.getPageNumber(), super.getRowCount(),
        this.gifticonSortType.getSort());
  }
}
