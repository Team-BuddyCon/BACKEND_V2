package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCountDto;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonCountResponseDto;
import yapp.buddycon.app.gifticon.application.port.in.GifticonCountUsecase;

@Tag(name = "기프티콘 갯수", description = "기프티콘 사용가능/사용완료 기프티콘 갯수 불러오기")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons/count")
public class GifticonCountController {

    private final GifticonCountUsecase usecase;

    @Operation(summary = "기프티콘 갯수 조회")
    @GetMapping("")
    public ResponseEntity<ResponseBody<GifticonCountResponseDto>> countGifticons(
            @Parameter(hidden = true) AuthUser authUser, GifticonCountDto dto) {
        long count = usecase.countGifticons(authUser.id(), dto);
        return ApiResponse.successWithBody("기프티콘 갯수를 조회하였습니다.", new GifticonCountResponseDto(count));
    }

}
