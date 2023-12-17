package yapp.buddycon.app.gifticon;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonUpdateDto;
import yapp.buddycon.app.gifticon.application.port.in.UpdateGifticonUsecase;
import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateGifticonControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @MockBean
    AuthenticationArgumentResolver authenticationArgumentResolver;

    @MockBean
    UpdateGifticonUsecase usecase;

    @Test
    void 정상적인_기프티콘_수정_요청의_경우_200이_반환된다() throws Exception {

        // given
        final var request = new GifticonUpdateDto(1L, "name", LocalDate.now(), GifticonStore.GS25, "memo");

        when(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthUser(1L));
        doNothing().when(usecase).update(request, 1L);

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().put("/api/v1/gifticons")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 입력값의_이름이_비어있을_경우_400을_반환한다() throws Exception {

        // given
        final var request = new GifticonUpdateDto(1L, " ", LocalDate.now(), GifticonStore.GS25, "memo");

        when(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new AuthUser(1L));
        doNothing().when(usecase).update(request, 1L);

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().put("/api/v1/gifticons")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 만료일이_형식에_맞지_않을경우_400을_반환한다() throws Exception {

        // given
        final var request = new GifticonUpdateDto(1L, "name", null, GifticonStore.GS25, "memo");
        String body = new ObjectMapper().writeValueAsString(request);

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().put("/api/v1/gifticons")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 존재하지_않는_기프티콘_스토어의_경우_400을_반환한다() throws Exception {

        // given
        final var dto = """
                {\\"gifticonId\\":1, \\"name\\":\\"name\\", \\"expireDate\\":\\"2022-04-01\\", \\"store\\":\\"GS24\\", \\"memo\\":\\"memo\\"}
                """;

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when().put("/api/v1/gifticons")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}