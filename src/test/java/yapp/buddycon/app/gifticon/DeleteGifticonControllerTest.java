package yapp.buddycon.app.gifticon;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import yapp.buddycon.app.auth.adapter.AuthenticationArgumentResolver;
import yapp.buddycon.app.common.AuthUser;
import yapp.buddycon.app.gifticon.adapter.GifticonException;
import yapp.buddycon.app.gifticon.application.port.in.DeleteGifticonUsecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeleteGifticonControllerTest {

    @MockBean
    DeleteGifticonUsecase usecase;

    @MockBean
    private AuthenticationArgumentResolver argumentResolver;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() throws Exception {
        when(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthUser(1L));
        when(argumentResolver.supportsParameter(any())).thenReturn(true);
        RestAssured.port = port;
    }

    @Test
    void 기프티콘이_정상적으로_제거된다() {
        doNothing().when(usecase).delete(1L, 1L);

        // when
        Response response = RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "accessToken")
                .delete("/api/v1/gifticons?gifticonId=1");

        response
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    @Test
    void userId와_gifticonId에_매칭되는_쿠폰이_없는_경우_에러를_반환한다() {
        doThrow(new GifticonException(GifticonException.GifticonExceptionCode.GIFTICON_NOT_FOUND)).when(usecase).delete(1L, 1L);

        // when
        Response response = RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "accessToken")
                .delete("/api/v1/gifticons?gifticonId=1");

        response
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
    }

}