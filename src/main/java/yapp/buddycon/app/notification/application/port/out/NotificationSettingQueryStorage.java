package yapp.buddycon.app.notification.application.port.out;

import java.util.Optional;
import yapp.buddycon.app.notification.adapter.client.response.NotificationSettingResponseDTO;

public interface NotificationSettingQueryStorage {

  Optional<NotificationSettingResponseDTO> findByUserId(Long userId);

}
