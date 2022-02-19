package project.sda.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists!");
//        String.format("User with %s already exists!",username);
    }
}
