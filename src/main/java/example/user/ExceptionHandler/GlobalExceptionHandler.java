package example.user.ExceptionHandler;


import example.user.Exceptions.InvalidAuthDetails;
import example.user.Exceptions.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAuthDetails.class)
    public ResponseEntity<String> invalidAuth(InvalidAuthDetails invalidAuthDetails) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(invalidAuthDetails.getMessage());
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<String> userAlreadyExist(UserAlreadyExist userAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExist.getMessage());
    }
}
