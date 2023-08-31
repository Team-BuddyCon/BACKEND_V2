package yapp.buddycon.app.auth.adapter.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yapp.buddycon.app.auth.adapter.jwt.exception.*;
import yapp.buddycon.common.AuthUser;

@Component
public class JwtTokenDecryptor {

    @Value("${security.jwt.token.bearer-type}")
    private String TOKEN_TYPE;
    @Value("${security.jwt.token.secret-key}")
    private String SECRET_KEY;

    public AuthUser decrypt(String authHeader) {
        String jwtToken = validateAndGetJwtToken(authHeader);
        try {
            return new AuthUser(Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken).getBody().get("id", Long.class));
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("만료된 토큰입니다.");
        } catch (SecurityException | MalformedJwtException e) {
            throw new MalformedTokenException("비정상적인 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedTokenException("지원되지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }
    }

    private String validateAndGetJwtToken(String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            throw new EmptyTokenException("토큰이 존재하지 않습니다.");
        } else if (!authHeader.split(" ")[0].trim().equals(TOKEN_TYPE)) {
            throw new InvalidTokenTypeException("Bearer 토큰 타입이 아닙니다.");
        } else {
            return authHeader.split(" ")[1].trim();
        }

    }
}
