package by.euroholl.userservice.security.jwt;

import by.euroholl.userservice.security.jwt.dto.UserJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    private static String jwtIssuer;
    private static String secretVersion;
    private static SecretManagerServiceClient secretManagerServiceClient;

    @Value("${jwt.issuer}")
    public void setJwtIssuer(String jwtIssuer) {
        JwtTokenUtil.jwtIssuer = jwtIssuer;
    }
    @Value("${gcp.secret}")
    public void setSecretVersion(String secretVersion) {
        JwtTokenUtil.secretVersion = secretVersion;
    }
    @Autowired
    public void setSecretManagerServiceClient(SecretManagerServiceClient secretManagerServiceClient) {
        JwtTokenUtil.secretManagerServiceClient = secretManagerServiceClient;
    }

    private static String getSecret() {
        SecretPayload payload = secretManagerServiceClient.accessSecretVersion(secretVersion).getPayload();
        return payload.getData().toStringUtf8();
    }

    public static String generateAccessToken(Authentication auth) {
        String jwtSecret = getSecret();

        Date now = new Date();
        Date validity = new Date(now.getTime() + TimeUnit.DAYS.toMillis(1));

        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("roles", auth.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtIssuer)
                .setIssuedAt(now)
                .setExpiration(validity) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static UserJWT getUser(String token) throws JsonProcessingException {
        String jwtSecret = getSecret();

        Claims claims = validate(token, jwtSecret);

        return new UserJWT(claims.getSubject(), (List<GrantedAuthority>) claims.get("roles"));
    }

    public static Claims validate(String token, String secret) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

           /* Date expiration = claims.getExpiration();
            if(expiration.before(new Date())) {

                //need my exception
                throw new RuntimeException("Date off");
            }*/

            return claims;

        } catch (SignatureException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException |
                 MalformedJwtException ex) {

            //need my exception
           throw new RuntimeException("Invalid token");
        }
    }
}