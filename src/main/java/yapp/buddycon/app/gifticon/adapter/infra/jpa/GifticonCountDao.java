package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GifticonCountDao {

    private final GifticonJpaRepository repository;

    public Long countGifticons(Long userId, boolean used) {
        return repository.countByUserIdAndUsed(userId, used);
    }
}
