package yapp.buddycon.web.gifticon.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonSearchParam;

public interface GifticonQueryPort {

  Page<GifticonVO> findAll(GifticonSearchParam param, Pageable pageable);

}
