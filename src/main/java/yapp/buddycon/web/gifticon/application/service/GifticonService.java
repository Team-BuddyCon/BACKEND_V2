package yapp.buddycon.web.gifticon.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.web.gifticon.adapter.in.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.in.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.port.in.GifticonUseCase;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GifticonService implements GifticonUseCase {

  @Override
  public Page<GifticonVO> getGifticons(SearchGifticonDTO dto) {
    return null;
  }

}
