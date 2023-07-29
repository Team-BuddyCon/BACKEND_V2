package yapp.buddycon.web.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;

public interface GifticonUseCase {

  Page<GifticonVO> getGifticons(SearchGifticonDTO dto);

}
