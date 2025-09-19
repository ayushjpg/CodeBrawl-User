package example.user.Filter;

import example.user.Config.JWTUtil;
import example.user.Exceptions.InvalidAuthDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String auth = request.getHeader("Authorization");
        String uri = request.getRequestURI();
        System.out.println(uri);

        if(uri.contains("/login") || uri.contains("/signup")){
            chain.doFilter(request, response);
            System.out.println("contiansss");
            return;
        }
        if (auth != null && auth.startsWith("Bearer ")) {
            String jwt = auth.substring(7);
            System.out.println("contiansss");
            try{
                Claims claims = jwtUtil.validateToken(jwt);
                 System.out.println(claims.getSubject());
                String username = claims.getSubject();
                String role = "ROLE_"+claims.get("role", String.class);


                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, jwt, Collections.singletonList(new SimpleGrantedAuthority(role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                throw new InvalidAuthDetails("Invalid JWT");
            }
        }else{
            throw new ServletException("Authorization header is missing");
        }

        chain.doFilter(request, response);
    }
}
