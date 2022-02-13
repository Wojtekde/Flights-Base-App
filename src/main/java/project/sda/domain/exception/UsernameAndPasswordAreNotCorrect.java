package project.sda.domain.exception;

public class UsernameAndPasswordAreNotCorrect extends RuntimeException {

    public UsernameAndPasswordAreNotCorrect() {
        super("User with these credentials does not exist");
    }
}
