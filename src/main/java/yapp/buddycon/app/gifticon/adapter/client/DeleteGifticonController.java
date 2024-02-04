package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.gifticon.application.port.in.DeleteGifticonUsecase;

@Tag(name = "기프티콘 제거", description = "기프티콘 제거 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class DeleteGifticonController {

    private final DeleteGifticonUsecase usecase;

    @Operation(summary = "기프티콘 제거")
    @DeleteMapping("")
    public ResponseEntity<ResponseBody<Void>> deleteGifticon(
            @Parameter(hidden = true) AuthUser user,
            @Parameter(name = "제거할 기프티콘 id") @RequestParam("gifticonId") Long gifticonId
    ) {
        usecase.delete(user.id(), gifticonId);
        return ApiResponse.success("기프티콘을 성공적으로 제거하였습니다");
    }
}