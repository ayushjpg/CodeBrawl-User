package example.user.Controller;

import example.user.DTO.SignupDTO;
import example.user.Model.User;
import example.user.Repository.UserRepository;
import example.user.Service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SignupController {


    private final SignupService signupService;

    public SignupController(SignupService signupService) {

        this.signupService = signupService;
    }

    @PostMapping("/signup")
    ResponseEntity<String> signup(@RequestBody SignupDTO user) {
       return signupService.signup(user);
    }
}
