package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @PutMapping("{gifticon-id}")
    public ResponseEntity<ResponseBody<Void>> updateGifticonContent(
            @Parameter(hidden = true) AuthUser user,
            @PathVariable("gifticon-id") long gifticonId,
            @Parameter(name = "기프티콘 수정 dto") @RequestBody @Valid GifticonUpdateDto dto
    ) {
        usecase.update(dto, gifticonId, user.id());
        return ApiResponse.success("기프티콘 수정을 완료하였습니다.");
    }

    @Operation(summary = "기프티콘 사용 여부 변경")
    @PatchMapping("{gifticon-id}")
    public ResponseEntity<ResponseBody<Void>> updateGifticonUsed(
            @Parameter(hidden = true) AuthUser user,
            @PathVariable("gifticon-id") long gifticonId,
            @Parameter(name = "기프티콘 사용 여부") @RequestParam("used") boolean used
    ) {
        usecase.updateUsed(used, gifticonId, user.id());
        return ApiResponse.success("기프티콘 사용 여부를 변경하였습니다.");
    }
}
