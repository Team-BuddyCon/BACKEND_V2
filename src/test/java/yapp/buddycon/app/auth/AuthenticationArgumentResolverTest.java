package yapp.buddycon.app.auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import yapp.buddycon.app.auth.adapter.AuthenticationArgumentResolver;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenDecryptor;
import yapp.buddycon.common.AuthUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationArgumentResolverTest {

    @MockBean
    JwtTokenDecryptor decryptor;

    @MockBean
    NativeWebRequest webRequest;

    @SpyBean
    AuthenticationArgumentResolver authenticationArgumentResolver;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 매개변수에_AuthUser가_존재하는경우_Argument_resolver를_거친다() throws Exception {
        // given
        final var authHeader = "Bearer tokenExample";
        when(decryptor.decrypt(authHeader)).thenReturn(new AuthUser(1000L));
        String contentType = MediaType.APPLICATION_JSON_VALUE;

        // when
        Response response = RestAssured
            .given()
                .log().all()
                .contentType(contentType)
            .when()
                .header("Authorization", authHeader).get("/test/auth");

        // then
        String resultString = response.then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract().asString();
        verify(authenticationArgumentResolver, times(1)).resolveArgument(any(), any(), any(), any());
        verify(decryptor, times(1)).decrypt(anyString());
        assertThat(resultString).isEqualTo("1000");
    }

    @Test
    void 매개변수에_AuthUser가_존재하지_않는_경우_Argument_resolver를_거치지_않는다() throws Exception {
        // given
        final var authHeader = "Bearer tokenExample";
        when(decryptor.decrypt(authHeader)).thenReturn(new AuthUser(1000L));
        String contentType = MediaType.APPLICATION_JSON_VALUE;

        // when
        Response response = RestAssured
            .given()
                .log().all()
                .contentType(contentType)
            .when()
                .header("Authorization", authHeader).get("/test/no-auth");

        // then
        String resultString = response.then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract().asString();
        verify(authenticationArgumentResolver, times(0)).resolveArgument(any(), any(), any(), any());
        verify(decryptor, times(0)).decrypt(anyString());
        assertThat(resultString).isEqualTo("0");
    }

}

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping("/auth")
    private Long authUserMethod(AuthUser authUser) {
        return authUser.id();
    }

    @GetMapping("/no-auth")
    private Long noAuthUserMethod() {
        return 0l;
    }
}