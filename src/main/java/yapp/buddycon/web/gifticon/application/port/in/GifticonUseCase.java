package yapp.buddycon.web.gifticon.application.port.in;

import org.springframework.data.domain.Page;
import yapp.buddycon.web.gifticon.adapter.in.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonDetailVO;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;

public interface GifticonUseCase {

  Page<GifticonVO> getGifticons(SearchGifticonDTO dto);

  GifticonDetailVO getGifticonDetail(long gifticonId);

}
