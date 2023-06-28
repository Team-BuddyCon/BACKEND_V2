package yapp.buddycon.web.gifticon.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.adapter.out.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.adapter.out.jpa.GifticonSearchParam;
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
