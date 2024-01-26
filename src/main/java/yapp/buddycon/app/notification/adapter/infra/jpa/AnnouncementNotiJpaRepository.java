package yapp.buddycon.app.notification.adapter.infra.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO;

public interface AnnouncementNotiJpaRepository extends JpaRepository<AnnouncementNotiEntity, Long> {

  @Query(value = """
    SELECT new yapp.buddycon.app.notification.adapter.client.response.AnnouncementNotiResponseDTO
      (an.id, an.title, an.body, an.createdAt)
    FROM AnnouncementNotiEntity an
    WHERE an.id = :id
  """)
  Optional<AnnouncementNotiResponseDTO> findByIdAsResponseDTO(Long id);

}
