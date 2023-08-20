package yapp.buddycon.app.gifticon.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.app.gifticon.application.port.out.GifticonQueryPort;

@RequiredArgsConstructor
@Repository
public class GifticonRepository implements GifticonQueryPort {

  private final GifticonJpaRepository gifticonJpaRepository;

  @Override
  public Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable) {
    return gifticonJpaRepository.findAll(dto, pageable);
  }

}