package yapp.buddycon.app.gifticon.adapter.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.in.CreateGifticonUsecase;
import yapp.buddycon.app.common.AuthUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifticons")
public class CreateGifticonController {

    private final CreateGifticonUsecase usecase;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createGifticon(@Valid @RequestPart("dto") GifticonCreationDto dto, @RequestPart("image") MultipartFile image, AuthUser user) {
        usecase.createGifticon(dto, image, user.id());
        return ApiResponse.success("기프티콘을 성공적으로 등록하였습니다");
    }
}
