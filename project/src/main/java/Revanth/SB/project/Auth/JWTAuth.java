package Revanth.SB.project.Auth;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTAuth {
    private final String SECRET = "Zk9xYmN4RW1ZVnJ3c1N2eE5hR3R0d2hGdXJmZk5TYXQzOXBjZ2xkVnhUZ1J6V0ttQ1d0eA==";
    private final long EXPIRATION = 1000*60*60*2;
    private final Key secretkey= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generatetoken(String email){
        return Jwts.builder().
                setSubject(email).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).
                signWith(secretkey, SignatureAlgorithm.HS512).
                compact();
    }

    public String extracter(String token){
        return  Jwts.parser().setSigningKey(secretkey).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJWTtoken(String token){
        try{
            extracter(token);
           return  true;
        } catch (Exception e) {
            return false;
        }
    }
}
