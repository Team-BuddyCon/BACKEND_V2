package yapp.buddycon.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.common.BaseEntity;
import yapp.buddycon.user.domain.User;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon extends BaseEntity {

  private String name;
  private String barcode;
  private LocalDate expireDate;
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  private Store store;
  private String memo;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
