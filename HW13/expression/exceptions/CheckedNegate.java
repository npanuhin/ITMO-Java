package expression.exceptions;

import expression.*;


public class CheckedNegate extends Negate {
    public CheckedNegate(AbstractExpression content) {
        super(content);
    }

    @Override
    protected int count(int a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException(String.format("-%d", a));
        }
        return -a;
    }
}
