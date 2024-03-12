package yapp.buddycon.app.gifticon.adapter.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yapp.buddycon.app.common.response.ResponseBody;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.in.CreateGifticonUsecase;
import yapp.buddycon.app.common.AuthUser;

@Tag(name = "기프티콘 생성", description = "기프티콘 생성 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class CreateGifticonController {

    private final CreateGifticonUsecase usecase;

    @Operation(summary = "기프티콘 단건 추가")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseBody<Long>> createGifticon(
            @Parameter(name = "기프티콘 생성 DTO", description = "store필드의 경우 STARBUCKS, TWOSOME_PLACE, GONG_CHA, GS25, CU, MACDONALD, BASKIN_ROBBINS 중 하나의 string을 넣어주세요") @Valid @RequestPart(name = "dto") GifticonCreationDto dto,
            @Parameter(name = "기프티콘 이미지 파일") @RequestPart(name = "image") MultipartFile image,
            @Parameter(hidden = true) AuthUser user) {
        return ApiResponse.successWithBody("기프티콘을 성공적으로 등록하였습니다",
            usecase.createGifticon(dto, image, user.id()));
    }
}
