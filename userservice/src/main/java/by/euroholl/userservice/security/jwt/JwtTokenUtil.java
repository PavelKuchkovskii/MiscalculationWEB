package by.euroholl.userservice.security.jwt;

import by.euroholl.userservice.service.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtTokenUtil {


    private static final String jwtIssuer;
    private static final ObjectMapper mapper;

    static {
        jwtIssuer = "EuroHoll";
        mapper = new ObjectMapper();
    }

    public static String generateAccessToken(UserDTO user) {
        String jwtSecret = "NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw";

        Map<String, Object> map = new HashMap<>();

        UserToJwt userToJwt = new UserToJwt(user.getUuid(),
                user.getMail(),
                user.getRole(;
        try {
            map.put("user", mapper.writeValueAsString(userToJwt));
        } catch (JsonProcessingException e) {
            throw new JwtTokenGenerationException();
        }


        return Jwts.builder()
                .setClaims(map)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static UserToJwt getUser(String token) throws JsonProcessingException {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return mapper.readValue(claims.get("user").toString(), UserToJwt.class);
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException |
                 MalformedJwtException ex) {
            return false;
        }
    }
}