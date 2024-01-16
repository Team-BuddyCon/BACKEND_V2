package yapp.buddycon.app.notification.application.port.out;

import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;

public interface AnnouncementNotiQueryStorage {

  AnnouncementNotiResponseDTO getById(Long id);

}
