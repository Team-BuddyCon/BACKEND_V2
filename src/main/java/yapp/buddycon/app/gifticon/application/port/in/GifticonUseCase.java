package yapp.buddycon.app.gifticon.application.port.in;

import org.springframework.data.domain.Slice;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.common.request.PagingDTO;

public interface GifticonUseCase {

  Slice<GifticonResponseDTO> getUnavailableGifticons(OAuthMemberInfo oAuthMemberInfo, PagingDTO dto);

  Slice<GifticonResponseDTO> getAvailableGifticons(OAuthMemberInfo oAuthMemberInfo, SearchAvailableGifticonDTO dto);

}
