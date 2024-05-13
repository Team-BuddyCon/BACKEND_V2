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
import yapp.buddycon.app.gifticon.domain.GifticonStoreCategory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GifticonCountControllerTest {

    @MockBean
    AuthenticationArgumentResolver argumentResolver;
    @MockBean
    GifticonCountUsecase usecase;
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 정상적인_기프티콘_조회_요청시_200을_반환한다() throws Exception {
        when(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthUser(1L));
        when(usecase.countGifticons(anyLong(), any())).thenReturn(5L);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("used", false)
                .param("gifticonStoreCategory", GifticonStoreCategory.CAFE)
                .param("remainingDays", 30)
                .when().get("/api/v1/gifticons/count")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .extract();
    }

    @Test
    void Enum클래스에_정의되지_않은_값_요청시_400을_반환한다() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("used", false)
                .param("gifticonStoreCategory", "정의되지 않은 값")
                .param("remainingDays", 30)
                .when().get("/api/v1/gifticons/count")
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all()
                .extract();
    }
}