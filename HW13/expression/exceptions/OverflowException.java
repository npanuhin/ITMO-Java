package expression.exceptions;

public class OverflowException extends RuntimeException {
    public OverflowException(String cause) {
        super("Overflow in " + cause);
    }
}