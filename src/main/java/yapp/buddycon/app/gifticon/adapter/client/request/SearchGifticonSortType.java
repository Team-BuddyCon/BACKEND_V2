package yapp.buddycon.app.gifticon.adapter.client.request;

import static yapp.buddycon.app.gifticon.adapter.infra.entity.QGifticonEntity.gifticonEntity;

import com.querydsl.core.types.OrderSpecifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchGifticonSortType {

  EXPIRE_DATE("유효 기간 순", gifticonEntity.expireDate.desc()),
  CREATED_AT("생성 일시 순", gifticonEntity.createdAt.desc()),
  NAME("이름 순", gifticonEntity.name.asc()),
  ;

  private String description;
  private OrderSpecifier orderByQuery;


}
