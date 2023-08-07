package yapp.buddycon.app.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yapp.buddycon.app.auth.adapter.JwtTokenCreator;
import yapp.buddycon.app.auth.adapter.JwtTokenSecretKey;
import yapp.buddycon.app.auth.application.service.Token;
import yapp.buddycon.app.user.domain.User;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JwtTokenCreatorTest {

  @Test
  void accessToken은_payload에_id_정보를_포함한다(@Mock JwtTokenSecretKey jwtTokenSecretKey) {

    // given
    final JwtTokenCreator jwtTokenCreator = new JwtTokenCreator(jwtTokenSecretKey);
    final User user = new User(1L, 12345678L);
    final Date testDate = new Date();
    final String secretKey = "abcdefghijklmnopqrstuvwxyz12345678901234567890abcdefghijklmnopqrstuvwxyz12345678901234567890";

    when(jwtTokenSecretKey.getSecretKey()).thenReturn(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)));

    // when
    Token token = jwtTokenCreator.createToken(user, testDate, testDate, testDate);

    // then
    Map<String, String> map = makePayloadToJson(token);
    assertEquals(user.id().toString(), map.get("id"));
  }

  private static Map<String, String> makePayloadToJson(Token token) {
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String payload = new String(decoder.decode(token.accessToken().split("\\.")[1]));
    final Type mapType = new TypeToken<Map<String, String>>(){}.getType();
    Map<String, String> map = new Gson().fromJson(payload, mapType);
    return map;
  }
}