package yapp.buddycon.app.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenCreator;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenSecretKey;
import yapp.buddycon.app.auth.application.service.LocalTime;
import yapp.buddycon.app.auth.application.service.TokenDto;
import yapp.buddycon.app.user.domain.User;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenCreatorTest {

  @Test
  void accessToken은_payload에_id_정보를_포함한다(@Mock JwtTokenSecretKey jwtTokenSecretKey) {

    // given
    final var jwtTokenCreator = new JwtTokenCreator(jwtTokenSecretKey);
    final var user = new User(1L, 12345678L, "nickname", "email", "FEMALE", "10-20");
    final var testTime = new LocalTime().getNow();
    final var secretKey = "abcdefghijklmnopqrstuvwxyz12345678901234567890abcdefghijklmnopqrstuvwxyz12345678901234567890";

    when(jwtTokenSecretKey.getSecretKey()).thenReturn(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)));

    // when
    TokenDto tokenDto = jwtTokenCreator.createToken(user, testTime, testTime, testTime, false);

    // then
    Map<String, String> map = makePayloadToJson(tokenDto);
    assertEquals(user.id().toString(), map.get("id"));
  }

  private static Map<String, String> makePayloadToJson(TokenDto tokenDto) {
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String payload = new String(decoder.decode(tokenDto.accessToken().split("\\.")[1]));
    final Type mapType = new TypeToken<Map<String, String>>(){}.getType();
    Map<String, String> map = new Gson().fromJson(payload, mapType);
    return map;
  }
}