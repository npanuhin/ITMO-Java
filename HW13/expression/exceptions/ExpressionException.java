package expression.exceptions;

public class ExpressionException extends RuntimeException {
    public ExpressionException(String cause) {
        super("Exception in evaluation: " + cause);
    }
}