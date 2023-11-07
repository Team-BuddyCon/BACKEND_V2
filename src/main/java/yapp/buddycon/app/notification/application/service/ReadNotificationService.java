package yapp.buddycon.app.notification.application.service;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationUseCase;

@Service
public class ReadNotificationService implements ReadNotificationUseCase {

  @Override
  public Slice<NotificationResponseDTO> getNotifications(Long userId, PagingDTO dto) {
    return null;
  }
}
