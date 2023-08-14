package yapp.buddycon.web.gifticon.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yapp.buddycon.web.gifticon.adapter.request.SearchGifticonDTO;
import yapp.buddycon.web.gifticon.adapter.response.GifticonResponseDTO;
import yapp.buddycon.web.gifticon.infra.jpa.GifticonJpaRepository;
import yapp.buddycon.web.gifticon.application.port.out.GifticonQueryPort;

@RequiredArgsConstructor
@Repository
public class GifticonRepository implements GifticonQueryPort {

  private final GifticonJpaRepository gifticonJpaRepository;

  @Override
  public Page<GifticonResponseDTO> findAll(SearchGifticonDTO dto, Pageable pageable) {
    return gifticonJpaRepository.findAll(dto, pageable);
  }

}
