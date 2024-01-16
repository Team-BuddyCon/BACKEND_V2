package yapp.buddycon.app.notification.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.notification.adapter.AnnouncementNotiException;
import yapp.buddycon.app.notification.adapter.AnnouncementNotiException.AnnouncementNotiExceptionCode;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;
import yapp.buddycon.app.notification.adapter.infra.jpa.AnnouncementNotiJpaRepository;
import yapp.buddycon.app.notification.application.port.out.AnnouncementNotiQueryStorage;

@RequiredArgsConstructor
@Component
public class JpaAnnouncementNotiQueryStorage implements AnnouncementNotiQueryStorage {

  private final AnnouncementNotiJpaRepository announcementNotiJpaRepository;

  @Override
  public AnnouncementNotiResponseDTO getById(Long id) {
    return announcementNotiJpaRepository.findByIdAsResponseDTO(id)
        .orElseThrow(() -> new AnnouncementNotiException(
            AnnouncementNotiExceptionCode.ANNOUNCEMENT_NOTI_NOT_FOUND));
  }
}
