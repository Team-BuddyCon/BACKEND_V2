package yapp.buddycon.app.gifticon.adapter.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchGifticonSortType {

  EXPIRE_DATE("유효 기간 순"),
  CREATED_AT("생성 일시 순"),
  NAME("이름 순"),
  ;

  private String description;

}
