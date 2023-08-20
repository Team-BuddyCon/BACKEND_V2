package yapp.buddycon.app.gifticon.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.GifticonUseCase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStoragePort;
import yapp.buddycon.common.request.PagingDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GifticonService implements GifticonUseCase {

  private final GifticonQueryStoragePort gifticonQueryStoragePort;

  @Override
  public Slice<GifticonResponseDTO> getUnavailableGifticons(PagingDTO dto) {
    return gifticonQueryStoragePort.findAllUnavailableGifticons(dto.toPageable());
  }
}
