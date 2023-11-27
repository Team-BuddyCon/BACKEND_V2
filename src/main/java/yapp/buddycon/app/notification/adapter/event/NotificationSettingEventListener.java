package yapp.buddycon.app.notification.adapter.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.event.NotificationSettingCreationEvent;
import yapp.buddycon.app.notification.application.port.in.CreateNotificationSettingUseCase;

@RequiredArgsConstructor
@Component
public class NotificationSettingEventListener {

  private final CreateNotificationSettingUseCase createNotificationSettingUseCase;

  @EventListener
  public void handleCreationEvent(NotificationSettingCreationEvent event) {
    createNotificationSettingUseCase.create(event.userId());
  }

}
