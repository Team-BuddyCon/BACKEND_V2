package yapp.buddycon.app.notification.adapter.infra.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "gifticon_expiration_noti")
public class GifticonExpirationNotiEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gifticon_expiration_noti_id")
  private Long id;
  @Column(name = "notification_id", nullable = false)
  private Long notificationId;
  @Column(name = "gifticon_id", nullable = false)
  private Long gifticonId;
  @Column(name = "days_left", nullable = false)
  private Integer daysLeft;

  public GifticonExpirationNotiEntity(Long id, Long notificationId, Long gifticonId,
      Integer daysLeft) {
    this.id = id;
    this.notificationId = notificationId;
    this.gifticonId = gifticonId;
    this.daysLeft = daysLeft;
  }
}
