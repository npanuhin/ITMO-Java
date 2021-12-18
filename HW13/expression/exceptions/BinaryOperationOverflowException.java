package expression.exceptions;

public class BinaryOperationOverflowException extends RuntimeException {
    public BinaryOperationOverflowException(String operator, int a, int b) {
        super("Overflow exception in \"" + a + ' ' + operator + ' ' + b + '\"');
    }
}