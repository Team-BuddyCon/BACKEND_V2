package yapp.buddycon.app.gifticon.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryStoragePort;

@RequiredArgsConstructor
@Repository
public class GifticonQueryStorage implements GifticonQueryStoragePort {

  private final GifticonJpaRepository gifticonJpaRepository;

  @Override
  public Slice<GifticonResponseDTO> findAllUnavailableGifticons(Pageable pageable) {
    return gifticonJpaRepository.findAllByUsedIsTrue(pageable);
  }
}
