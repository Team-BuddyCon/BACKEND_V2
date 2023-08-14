package yapp.buddycon.web.gifticon.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonSearchParam;
import yapp.buddycon.web.gifticon.application.port.in.GifticonUseCase;
import yapp.buddycon.web.gifticon.application.port.out.GifticonQueryPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GifticonService implements GifticonUseCase {

  private final GifticonQueryPort gifticonQueryPort;

  @Override
  public Page<GifticonResponseDTO> getGifticons(SearchGifticonDTO dto) {
    return gifticonQueryPort.findAll(GifticonSearchParam.valueOf(dto),
        PageRequest.of(dto.getPageNumber(), dto.getRowCount()));
  }

}
