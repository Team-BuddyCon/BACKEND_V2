package yapp.buddycon.app.notification.application.port.in;

import org.springframework.data.domain.Slice;
import yapp.buddycon.app.common.request.PagingDTO;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;

public interface ReadNotificationUseCase {

  Slice<NotificationResponseDTO> getNotifications(Long userId, PagingDTO dto);

  AnnouncementNotiResponseDTO getAnnouncementNoti(Long userId, Long announcementId);

}
