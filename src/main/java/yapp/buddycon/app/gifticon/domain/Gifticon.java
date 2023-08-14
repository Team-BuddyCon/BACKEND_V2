package yapp.buddycon.app.gifticon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
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
public class Gifticon extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gifticon_id")
  private Long id;

  @Column(name = "barcode", nullable = false)
  private String barcode;
  @Column(name = "image_url", nullable = false)
  private String imageUrl;
  @Column(name = "name", nullable = false, length = 16)
  private String name;
  @Column(name = "memo", length = 20)
  private String memo;
  @Column(name = "expire_date", nullable = false)
  private LocalDate expireDate;
  private boolean used;

  @ManyToOne
  @JoinColumn(name = "store_id", referencedColumnName = "store_id")
  private Store store;

}
