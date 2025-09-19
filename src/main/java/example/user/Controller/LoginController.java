package example.user.Controller;

import example.user.DTO.LoginRequest;
import example.user.Service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user) {
        return loginService.login(user);
    }
}
