package pl.switix.springjwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import pl.switix.springjwt.entity.AppUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "zaq1@WSX";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public <T> T extractClaim(String token, Function<DecodedJWT, T> claimResolver) {
        final DecodedJWT claims = JWT.decode(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, appUser.getUsername());
    }

    public boolean validateToken(String token, AppUser appUser) {
        final String username = extractUsername(token);
        return (username.equals(appUser.getUsername()) && !isTokenExpired(token));
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return JWT.create()
                .withPayload(claims)
                .withSubject(subject)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10)))
                .sign(ALGORITHM);
    }


    public String extractUsername(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
