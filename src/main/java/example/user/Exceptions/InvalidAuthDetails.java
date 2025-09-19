package example.user.Exceptions;


public class InvalidAuthDetails extends RuntimeException {
    public InvalidAuthDetails(String message) {
        super(message);
    }
}
