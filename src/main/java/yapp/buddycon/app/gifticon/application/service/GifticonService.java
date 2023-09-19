package yapp.buddycon.app.gifticon.application.service;

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

  private final GifticonQueryStorage gifticonQueryStorage;

  @Override
  public Slice<GifticonResponseDTO> getUnavailableGifticons(Long userId, PagingDTO dto) {
    return gifticonQueryStorage.findAllUnavailableGifticons(userId, dto.toPageable());
  }

  @Override
  public Slice<GifticonResponseDTO> getAvailableGifticons(Long userId, SearchAvailableGifticonDTO dto) {
    return gifticonQueryStorage.findAllAvailableGifticons(
        userId,
        dto.getGifticonStoreCategory(),
        dto.toPageable());
  }
}
