package yapp.buddycon.app.gifticon.application.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.GifticonUseCase;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStorage;
import yapp.buddycon.common.request.PagingDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GifticonService implements GifticonUseCase {

  private final GifticonQueryStorage gifticonQueryStoragePort;

  @Override
  public Slice<GifticonResponseDTO> getUnavailableGifticons(PagingDTO dto) {
    return gifticonQueryStoragePort.findAllUsedGifticons(dto.toPageable());
  }

  @Override
  public Slice<GifticonResponseDTO> getAvailableGifticons(SearchAvailableGifticonDTO dto) {
    return gifticonQueryStoragePort.findAllAvailableGifticons(
        LocalDate.now(),
        dto.getGifticonStoreCategory(),
        dto.getGifticonSortType(),
        dto.toPageable());
  }
}
