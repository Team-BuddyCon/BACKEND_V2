package yapp.buddycon.app.notification.adapter.infra.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notification_setting")
public class NotificationSettingEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_setting_id")
  private Long id;
  @Column(name = "user_id", nullable = false, unique = true)
  private Long userId;
  private boolean activated;
  private boolean fourteenDaysBefore;
  private boolean sevenDaysBefore;
  private boolean threeDaysBefore;
  private boolean oneDayBefore;
  private boolean theDay;

  @Builder
  public NotificationSettingEntity(Long userId) {
    this.userId = userId;
    this.activated = true;
    this.fourteenDaysBefore = false;
    this.sevenDaysBefore = true;
    this.threeDaysBefore = false;
    this.oneDayBefore = true;
    this.theDay = true;
  }

}
