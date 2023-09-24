package yapp.buddycon.app.gifticon;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenDecryptor;
import yapp.buddycon.app.common.response.BadRequestException;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.GifticonUseCase;
import yapp.buddycon.common.AuthUser;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GifticonControllerRestAssuredTest {

    @MockBean
    private GifticonUseCase gifticonUseCase;

    @MockBean
    private JwtTokenDecryptor decryptor;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Nested
    class getGifticon {

        @BeforeEach
        void init() {
            when(decryptor.decrypt(anyString())).thenReturn(new AuthUser(1000L));
        }

        @Test
        void 요청한_기프티콘의_권한을_가진_사용자가_요청할시_200이_반환된다() {
            // given
            when(gifticonUseCase.getGifticon(anyLong(), anyLong())).thenReturn(new GifticonResponseDTO());

            // when
            Response response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "abc")
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
            when(gifticonUseCase.getGifticon(anyLong(), anyLong())).thenThrow(new BadRequestException(""));

            // when
            Response response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "abc")
                .get("/api/v1/gifticons/" + "1");

            // then
            response
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
        }
    }

}
