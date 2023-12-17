package yapp.buddycon.app.gifticon.adapter.client.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import yapp.buddycon.app.common.validation.ValidatedEnum;
import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.time.LocalDate;

public record GifticonUpdateDto(

        @NotBlank
        String name,

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate expireDate,
        @ValidatedEnum(enumClass = GifticonStore.class)
        GifticonStore store,

        String memo
) {
}
