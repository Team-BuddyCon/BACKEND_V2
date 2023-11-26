package yapp.buddycon.app.gifticon;

import io.restassured.RestAssured;
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
import yapp.buddycon.app.gifticon.application.port.in.GifticonCountUsecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GifticonCountControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @MockBean
    AuthenticationArgumentResolver argumentResolver;
    @MockBean
    GifticonCountUsecase usecase;

    @Test
    void 정상적인_기프티콘_조회_요청시_200을_반환한다() throws Exception {
        when(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthUser(1L));
        when(usecase.countGifticons(1L, false)).thenReturn(5L);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("used", false)
                .when().get("/api/v1/gifticons/count")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .extract();
    }
}