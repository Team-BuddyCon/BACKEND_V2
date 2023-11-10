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
@Table(name = "announcement_noti")
public class AnnouncementNotiEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "announcement_noti_id")
  private Long id;
  @Column(name = "notification_id", nullable = false)
  private Long notificationId;
  private String title;
  private String body;

  public AnnouncementNotiEntity(Long id, Long notificationId, String title, String body) {
    this.id = id;
    this.notificationId = notificationId;
    this.title = title;
    this.body = body;
  }
}
