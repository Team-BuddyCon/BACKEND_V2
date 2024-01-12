package yapp.buddycon.app.notification.adapter.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import yapp.buddycon.app.event.NotificationCheckingEvent;
import yapp.buddycon.app.event.NotificationSettingCreationEvent;
import yapp.buddycon.app.notification.application.port.in.CreateNotificationSettingUseCase;
import yapp.buddycon.app.notification.application.port.in.UpdateNotificationSettingUseCase;

@RequiredArgsConstructor
@Component
public class NotificationSettingEventListener {

  private final CreateNotificationSettingUseCase createNotificationSettingUseCase;
  private final UpdateNotificationSettingUseCase updateNotificationSettingUseCase;

  @EventListener
  public void handleCreationEvent(NotificationSettingCreationEvent event) {
    createNotificationSettingUseCase.create(event.userId());
  }

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleCheckingEvent(NotificationCheckingEvent event) {
    updateNotificationSettingUseCase.updateNotificationLastCheckedAt(event.userId(), event.checkedAt());
  }

}
