package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;
import yapp.buddycon.app.notification.application.port.out.AnnouncementNotiQueryStorage;

@RequiredArgsConstructor
@Component
public class JpaAnnouncementNotiQueryStorage implements AnnouncementNotiQueryStorage {

  @Override
  public AnnouncementNotiResponseDTO getById(Long id) {
    // TODO implement
    return null;
  }
}
