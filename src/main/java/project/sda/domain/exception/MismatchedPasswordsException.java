package project.sda.domain.exception;

public class MismatchedPasswordsException extends RuntimeException {
    public MismatchedPasswordsException() {
        super("The given passwords do not match");
    }
}
