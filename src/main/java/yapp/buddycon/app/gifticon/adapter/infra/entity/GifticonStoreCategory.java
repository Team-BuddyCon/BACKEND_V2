package yapp.buddycon.app.gifticon.adapter.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GifticonStoreCategory {

  CAFE("카페"),
  CONVENIENCE_STORE("편의점"),
  OTHERS("기타"),
  ;

  private String name;

}
