package example.user.Service;

import example.user.DTO.SignupDTO;
import example.user.Exceptions.UserAlreadyExist;
import example.user.Model.User;
import example.user.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signup(SignupDTO user) {

        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExist(user.getUsername() +" already exists");
        }
        try{
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setRole("user");
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created Proceed with Login");
        }catch(Exception e){
            throw new RuntimeException("Error while creating new user /n"+ e.getMessage());
        }


    }
}
