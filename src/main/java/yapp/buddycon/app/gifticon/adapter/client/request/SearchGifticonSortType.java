package yapp.buddycon.app.gifticon.adapter.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum SearchGifticonSortType {

  EXPIRE_DATE("유효 기간 순", Sort.by("expireDate").descending()),
  CREATED_AT("생성 일시 순", Sort.by("createdAt").descending()),
  NAME("이름 순", Sort.by("name").ascending()),
  ;

  private String description;
  private Sort sort;

}
