package yapp.buddycon.app.gifticon;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenProvider;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.adapter.GifticonException.GifticonExceptionCode;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.ReadGifticonUseCase;
import yapp.buddycon.app.user.domain.User;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadGifticonControllerRestAssuredTest {

    @MockBean
    private ReadGifticonUseCase gifticonUseCase;

    @Autowired
    private JwtTokenProvider provider;

    private String ACCESS_TOKEN;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        TokenDto tokenDto = provider.provide(new User(1000L, 1L, "test", "test", "test", "test"));
        ACCESS_TOKEN = "Bearer " + tokenDto.accessToken();
    }

    @Nested
    class getGifticon {

        @Test
        void 요청한_기프티콘의_권한을_가진_사용자가_요청할시_200이_반환된다() {
            // given
            GifticonResponseDTO gifticonResponseDTO =
                new GifticonResponseDTO(1L, "", "", "", null, null, null);
            when(gifticonUseCase.getGifticon(anyLong(), anyLong())).thenReturn(gifticonResponseDTO);

            // when
            Response response = RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .header("Authorization", ACCESS_TOKEN)
                    .get("/api/v1/gifticons/" + "1");

            // then
            response
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
        }

        @Test
        void 요청한_기프티콘을_찾을_수_없을시_404가_반환된다() {
            // given
            when(gifticonUseCase.getGifticon(anyLong(), anyLong())).thenThrow(
                new GifticonException(GifticonExceptionCode.GIFTICON_NOT_FOUND));

            // when
            Response response = RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .header("Authorization", ACCESS_TOKEN)
                    .get("/api/v1/gifticons/" + "1");

            // then
            response
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
        }
    }

}
