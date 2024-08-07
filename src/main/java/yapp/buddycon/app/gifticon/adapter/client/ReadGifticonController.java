package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.gifticon.adapter.client.request.SearchAvailableGifticonDTO;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.adapter.client.response.SingleGifticonResponseDto;
import yapp.buddycon.app.gifticon.application.port.in.ReadGifticonUseCase;
import yapp.buddycon.app.common.request.PagingDTO;

@Tag(name = "기프티콘 조회", description = "기프티콘 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class ReadGifticonController {

  private final ReadGifticonUseCase gifticonUseCase;

  @Operation(summary = "사용완료 기프티콘 목록 조회")
  @GetMapping("/unavailable")
  public ResponseEntity<ResponseBody<Slice<GifticonResponseDTO>>> getUnavailableGifticons(
          @Parameter(hidden = true) AuthUser authUser, @Valid PagingDTO dto) {
    return ApiResponse.successWithBody("사용완료 기프티콘 목록을 성공적으로 조회하였습니다.", gifticonUseCase.getUnavailableGifticons(authUser.id(), dto));
  }

  @Operation(summary = "사용가능 기프티콘 목록 조회")
  @GetMapping("/available")
  public ResponseEntity<ResponseBody<Slice<GifticonResponseDTO>>> getAvailableGifticons(
          @Parameter(hidden = true) AuthUser authUser, @Valid SearchAvailableGifticonDTO dto) {
    return ApiResponse.successWithBody("사용가능 기프티콘 목록을 성공적으로 조회하였습니다.", gifticonUseCase.getAvailableGifticons(authUser.id(), dto));
  }

  @Operation(summary = "특정 기프티콘 단건 조회")
  @GetMapping("/{gifticon-id}")
  public ResponseEntity<ResponseBody<SingleGifticonResponseDto>> getGifticon(
          @Parameter(hidden = true) AuthUser authUser, @PathVariable("gifticon-id") long gifticonId) {
    return ApiResponse.successWithBody("기프티콘을 성공적으로 조회하였습니다.",
        gifticonUseCase.getGifticon(authUser.id(), gifticonId));
  }

}
