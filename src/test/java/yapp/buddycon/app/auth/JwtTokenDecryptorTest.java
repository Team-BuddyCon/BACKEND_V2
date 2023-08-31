package yapp.buddycon.app.auth;

import org.junit.jupiter.api.Test;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenDecryptor;
import yapp.buddycon.app.auth.adapter.jwt.exception.EmptyTokenException;
import yapp.buddycon.app.auth.adapter.jwt.exception.InvalidTokenTypeException;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenDecryptorTest {

    JwtTokenDecryptor decryptor = new JwtTokenDecryptor();

    @Test
    void token이_null일_경우_에러를_반환한다() {
        assertThrows(EmptyTokenException.class, () -> decryptor.decrypt(null));
    }

    @Test
    void token이_비어있을_경우_에러를_반환한다() {
        assertThrows(EmptyTokenException.class, () -> decryptor.decrypt(""));
    }

    @Test
    void token이_Bearer_타입이_아닐_경우_에러를_반환한다() {
        assertThrows(InvalidTokenTypeException.class, () -> decryptor.decrypt("NotBearer jwtTokenExample"));
    }

}