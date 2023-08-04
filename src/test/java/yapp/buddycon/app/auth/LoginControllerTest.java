package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import yapp.buddycon.app.auth.application.service.OAuthMemberInfo;
import yapp.buddycon.app.auth.application.port.out.OAuthUserInfoApi;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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
}