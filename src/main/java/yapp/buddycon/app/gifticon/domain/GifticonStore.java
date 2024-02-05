package yapp.buddycon.app.gifticon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GifticonStore {

  STARBUCKS("스타벅스", GifticonStoreCategory.CAFE),
  TWOSOME_PLACE("투썸 플레이스", GifticonStoreCategory.CAFE),
  GONG_CHA("공차", GifticonStoreCategory.CAFE),
  HOLLYS("할리스", GifticonStoreCategory.CAFE),
  ANGELINUS("엔제리너스", GifticonStoreCategory.CAFE),
  COFFEE_BEAN("커피빈", GifticonStoreCategory.CAFE),
  MEGA_COFFEE("메가커피", GifticonStoreCategory.CAFE),

  GS25("지에스 25", GifticonStoreCategory.CONVENIENCE_STORE),
  CU("씨유", GifticonStoreCategory.CONVENIENCE_STORE),

  MACDONALD("맥도날드", GifticonStoreCategory.OTHERS),
  BASKIN_ROBBINS("배스킨 라빈스", GifticonStoreCategory.OTHERS),
  ;

  private String name;
  private GifticonStoreCategory category;

}
