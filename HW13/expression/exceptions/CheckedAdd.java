package expression.exceptions;

import expression.*;


public class CheckedAdd extends Add {
    public CheckedAdd(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected int count(int a, int b) {
        if (
            (b > 0 && Integer.MAX_VALUE - b < a) ||
            (b < 0 && Integer.MIN_VALUE - b > a)
        ) {
            throw new OverflowException(String.format("%d + %d", a, b));
        }
        return a + b;
    }
}
