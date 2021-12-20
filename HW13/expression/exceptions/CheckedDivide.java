package expression.exceptions;

import expression.*;


public class CheckedDivide extends Divide {
    public CheckedDivide(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected int count(int a, int b) throws OverflowException {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(String.format("%d / %d", a, b));
        }
        return a / b;
    }
}
