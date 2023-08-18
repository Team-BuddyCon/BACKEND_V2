package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.app.gifticon.domain.Gifticon;

public interface GifticonJpaRepository extends JpaRepository<Gifticon, Long>,
    GifticonJpaCustomRepository {

}
