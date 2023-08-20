package yapp.buddycon.app.gifticon.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;

public interface GifticonQueryStoragePort {

  Slice<GifticonResponseDTO> findAllUnavailableGifticons(Pageable pageable);

}
