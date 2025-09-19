package example.user.Config;

import example.user.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    public String generateToken(User userPrincipal) {

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("role",userPrincipal.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))



                .signWith(SignatureAlgorithm.HS256, "lodalodalodalodaloadlodalodlalalalalalalalalalalalaalodaldoada")
                .compact();
    }

    public Claims validateToken(String token) {
        try{
            JwtParser jwtParser = Jwts.parser()
                    .setSigningKey("lodalodalodalodaloadlodalodlalalalalalalalalalalalaalodaldoada")
                    .build();

            return jwtParser.parseClaimsJws(token).getBody();

        }
        catch(Exception e){}
        return null;
    }
}
