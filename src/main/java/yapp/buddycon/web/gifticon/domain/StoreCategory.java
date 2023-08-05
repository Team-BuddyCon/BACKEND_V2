package yapp.buddycon.web.gifticon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.common.BaseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StoreCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_category_id")
  private Long id;
  @Column(name = "name", nullable = false, length = 20)
  private String name;

}
