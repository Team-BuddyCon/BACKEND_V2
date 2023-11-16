package yapp.buddycon.app.notification.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;
import yapp.buddycon.app.notification.application.port.in.ReadNotificationUseCase;
import yapp.buddycon.app.notification.application.port.out.NotificationQueryStorage;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadNotificationService implements ReadNotificationUseCase {

  private final NotificationQueryStorage queryStorage;

  @Override
  public Slice<NotificationResponseDTO> getNotifications(Long userId, PagingDTO dto) {
    return queryStorage.findAllByUserId(userId, dto.toPageable());
  }
}
