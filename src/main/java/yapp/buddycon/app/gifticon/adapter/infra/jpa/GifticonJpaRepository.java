package yapp.buddycon.app.gifticon.adapter.infra.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.app.gifticon.adapter.infra.entity.GifticonEntity;

public interface GifticonJpaRepository extends JpaRepository<GifticonEntity, Long>,
    GifticonJpaCustomRepository {

  Slice<GifticonEntity> findAllByUsedIsTrue(Pageable pageable);

}
