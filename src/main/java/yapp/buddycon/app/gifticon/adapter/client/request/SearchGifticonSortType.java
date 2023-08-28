package yapp.buddycon.app.gifticon.adapter.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;

@Getter
@AllArgsConstructor
public enum SearchGifticonSortType {

  EXPIRE_DATE("유효 기간 순",
      Sort.sort(GifticonEntity.class).by(GifticonEntity::getExpireDate).descending()),
  CREATED_AT("생성 일시 순",
      Sort.sort(GifticonEntity.class).by(GifticonEntity::getCreatedAt).descending()),
  NAME("이름 순",
      Sort.sort(GifticonEntity.class).by(GifticonEntity::getName).ascending()),
  ;

  private String description;
  private Sort sort;

}
