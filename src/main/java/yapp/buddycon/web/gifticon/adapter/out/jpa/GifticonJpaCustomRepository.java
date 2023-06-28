package yapp.buddycon.web.gifticon.adapter.out.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;

public interface GifticonJpaCustomRepository {

  Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable);

}
