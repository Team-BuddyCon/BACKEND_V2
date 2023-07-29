package yapp.buddycon.web.gifticon.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.web.gifticon.domain.Gifticon;

public interface GifticonJpaRepository extends JpaRepository<Gifticon, Long>,
    GifticonJpaCustomRepository {

}
