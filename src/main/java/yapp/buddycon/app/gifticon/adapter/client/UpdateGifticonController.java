package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;
import yapp.buddycon.app.gifticon.application.port.in.UpdateGifticonUsecase;

@Tag(name = "기프티콘 수정", description = "기프티콘 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class UpdateGifticonController {

    private final UpdateGifticonUsecase usecase;

    @Operation(summary = "기프티콘 내용 변경")
    @PutMapping("")
    public ResponseEntity<ResponseBody> updateGifticonContent(
            @Parameter(hidden = true) AuthUser user,
            @Parameter(name = "기프티콘 수정 dto") @RequestBody @Valid GifticonUpdateDto dto
    ) {
        usecase.update(dto, user.id());
        return ApiResponse.success("기프티콘 수정을 완료하였습니다.");
    }
}
