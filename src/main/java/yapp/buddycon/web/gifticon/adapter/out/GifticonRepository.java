package yapp.buddycon.web.gifticon.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.port.out.GifticonQueryPort;

@Repository
public class GifticonRepository implements GifticonQueryPort {

  @Override
  public Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable) {
    return null;
  }
}
