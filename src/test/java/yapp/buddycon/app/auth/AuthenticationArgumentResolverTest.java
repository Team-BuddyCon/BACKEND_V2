package yapp.buddycon.app.auth;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenDecryptor;
import yapp.buddycon.common.AuthUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationArgumentResolverTest {

    @MockBean
    JwtTokenDecryptor decryptor;

    @MockBean
    NativeWebRequest webRequest;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 매개변수에_AuthUser가_존재하는경우_Argument_resolver를_거친다() {
        final var authHeader = "Bearer tokenExample";
        when(decryptor.decrypt(authHeader)).thenReturn(new AuthUser(1000L));

        final var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().header("Authorization", authHeader).get("/test")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .extract().asString();

        // then
        assertThat(response).isEqualTo("1000");
    }

}

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping
    private Long authUserMethod(AuthUser authUser) {
        return authUser.id();
    }
}