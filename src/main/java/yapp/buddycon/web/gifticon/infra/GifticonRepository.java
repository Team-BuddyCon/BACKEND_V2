package yapp.buddycon.web.gifticon.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonSearchParam;
import yapp.buddycon.web.gifticon.application.port.out.GifticonQueryPort;

@RequiredArgsConstructor
@Repository
public class GifticonRepository implements GifticonQueryPort {

  private final GifticonJpaRepository gifticonJpaRepository;

  @Override
  public Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable) {
    return gifticonJpaRepository.findAll(param, pageable);
  }

}
