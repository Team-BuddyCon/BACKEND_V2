package yapp.buddycon.app.gifticon.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonMapper;
import yapp.buddycon.app.gifticon.application.port.out.GifticonCommandStorage;
import yapp.buddycon.app.gifticon.domain.Gifticon;

@RequiredArgsConstructor
@Component
public class JpaGifticonCommandStorage implements GifticonCommandStorage {

    private final GifticonMapper mapper;
    private final GifticonJpaRepository repository;

    @Override
    public Gifticon save(Gifticon gifticon) {

        return mapper.toGifticon(repository.save(mapper.toEntity(gifticon)));
    }

    @Override
    public void delete(Long userId, Long gifticonId) {
        repository.delete(userId, gifticonId);
    }
}
