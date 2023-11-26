package yapp.buddycon.app.notification.adapter.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingJpaRepository extends JpaRepository<NotificationSettingEntity, Long> {

  NotificationSettingEntity getByUserId(Long userId);

}
