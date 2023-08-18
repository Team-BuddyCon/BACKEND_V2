package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import yapp.buddycon.BuddyconApplication;
import yapp.buddycon.app.auth.adapter.oauth.OAuthRequestException;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = BuddyconApplication.class)
class LoginControllerTest {

    @MockBean
    OAuthUserInfoApi oAuthUserInfoApi;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 정상적인_액세스_토큰의_경우_로그인시_200이_반환된다() throws Exception {

        // given
        final var validAccessToken = "valid_access_token";
        final var body = "{\"accessToken\":\"" + validAccessToken + "\"}";

        when(oAuthUserInfoApi.call(validAccessToken)).thenReturn(new OAuthMemberInfo(1L));

        final var resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        resultActions.andExpect(status().isOk());

    }

    @Test
    void 정상적이지않은_액세스_토큰의_경우_로그인시_예외가_던져진다() throws Exception {

        // given
        final var invalidAccessToken = "invalid_access_token";
        final var body = "{\"accessToken\":\"" + invalidAccessToken + "\"}";

        when(oAuthUserInfoApi.call(invalidAccessToken)).thenThrow(new OAuthRequestException("올바르지않은 액세스토큰입니다."));

        final var resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        resultActions
                .andExpect(status().is5xxServerError())
                .andExpect((result) -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getClass().isAssignableFrom(OAuthRequestException.class)))
                .andReturn();

    }

    @Test
    void 액세스토큰_값이_blank라면_bad_request를_던진다() throws Exception {

        // given
        final var invalidAccessToken = "";
        final var body = "{\"accessToken\":\"" + invalidAccessToken + "\"}";

        final var resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );

        resultActions
                .andExpect(status().isBadRequest());

    }
}