package application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {
    private String secret = "secret2002";
    private long validMilliSecond = 3600000;

    public String generatedToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roleList);

        Date dateStart = new Date();
        Date expiredDate = new Date(dateStart.getTime() + validMilliSecond);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(details.getUsername())
                .setIssuedAt(dateStart)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserName(String token) {
        return getParserToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getParserToken(token).get("roles", List.class);
    }

    private Claims getParserToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
