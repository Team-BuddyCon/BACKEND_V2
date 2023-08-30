package yapp.buddycon.app.auth;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import yapp.buddycon.app.auth.adapter.oauth.OAuthRequestException;
import yapp.buddycon.app.auth.application.service.AuthService;
import yapp.buddycon.app.auth.adapter.LoginRequest;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @MockBean
    OAuthUserInfoApi oAuthUserInfoApi;

    @MockBean
    AuthService authService;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 정상적인_액세스_토큰의_경우_로그인시_200이_반환된다() {

        // given
        final var validAccessToken = "valid_access_token";
        final var request = new LoginRequest(validAccessToken, "nickname", "email@buddycon.com", "gender", "age");

        when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(new OAuthMemberInfo(1L));

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/v1/auth/login")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 정상적이지않은_액세스_토큰의_경우_로그인시_예외가_던져진다() {

        // given
        final var invalidAccessToken = "invalid_access_token";
        final var request = new LoginRequest(invalidAccessToken, "nickname", "email@buddycon.com", "gender", "age");

        when(authService.login(request)).thenThrow(new OAuthRequestException("올바르지않은 액세스토큰입니다."));

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/v1/auth/login")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 액세스토큰_값이_blank라면_bad_request를_던진다() {

        // given
        final var invalidAccessToken = "";
        final var request = new LoginRequest(invalidAccessToken, "nickname", "email@buddycon.com", "gender", "age");

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/v1/auth/login")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 닉네임이_blank라면_bad_request를_던진다() {

        // given
        final var validAccessToken = "valid_access_token";
        final var request = new LoginRequest(validAccessToken, "", "email@buddycon.com", "gender", "age");

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/v1/auth/login")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 이메일이_형식에_맞지_않다면_bad_request를_던진다() {

        // given
        final var validAccessToken = "valid_access_token";
        final var invalidEmail = "invalidEmail";
        final var request = new LoginRequest(validAccessToken, "nickname", invalidEmail, "gender", "age");

        // when
        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/v1/auth/login")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}