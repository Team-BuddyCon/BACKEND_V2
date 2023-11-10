package yapp.buddycon.app.notification.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.notification.adapter.client.response.NotificationResponseDTO;

public interface NotificationQueryStorage {

  Slice<NotificationResponseDTO> findAll(long userId, Pageable pageable);

}
