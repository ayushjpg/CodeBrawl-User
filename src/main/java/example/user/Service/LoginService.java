package example.user.Service;

import example.user.Config.JWTUtil;
import example.user.DTO.LoginRequest;
import example.user.Exceptions.InvalidAuthDetails;
import example.user.Model.User;
import example.user.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public LoginService(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public String login(LoginRequest user) {
        try{
            Authentication auth= authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()) );
            SecurityContextHolder.getContext().setAuthentication(auth);
            User userDetails = userRepository.findByUsername(user.getUsername());
            System.out.println(auth+ """
                    """);
            return jwtUtil.generateToken(userDetails);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new InvalidAuthDetails("Incorrect username or password");
        }

    }

}
