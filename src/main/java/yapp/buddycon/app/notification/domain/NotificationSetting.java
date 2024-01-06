package yapp.buddycon.app.notification.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationSetting {

  private Long id;
  private Long userId;
  private boolean activated;
  private boolean fourteenDaysBefore;
  private boolean sevenDaysBefore;
  private boolean threeDaysBefore;
  private boolean oneDayBefore;
  private boolean theDay;
  private LocalDateTime lastCheckedAt;

  public void update(boolean activated, boolean fourteenDaysBefore, boolean sevenDaysBefore,
      boolean threeDaysBefore, boolean oneDayBefore, boolean theDay) {
    this.activated = activated;
    this.fourteenDaysBefore = fourteenDaysBefore;
    this.sevenDaysBefore = sevenDaysBefore;
    this.threeDaysBefore = threeDaysBefore;
    this.oneDayBefore = oneDayBefore;
    this.theDay = theDay;
  }

  public void updateLastCheckedAt(LocalDateTime lastCheckedAt) {
    this.lastCheckedAt = lastCheckedAt;
  }

}
