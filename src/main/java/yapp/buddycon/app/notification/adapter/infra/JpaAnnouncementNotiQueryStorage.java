package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;
import yapp.buddycon.app.notification.application.port.out.AnnouncementNotiQueryStorage;

@RequiredArgsConstructor
@Component
public class JpaAnnouncementNotiQueryStorage implements AnnouncementNotiQueryStorage {

  @Override
  public AnnouncementNotiResponseDTO getByIdAndUserId(Long id, Long userId) {
    // TODO implement
    return null;
  }
}
