package expression.exceptions;

import expression.*;


public class CheckedAbs extends Abs {
    public CheckedAbs(AbstractExpression content) {
        super(content);
    }

    @Override
    protected int count(int a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException(String.format("abs(%d)", a));
        }
        return a < 0 ? -a : a;
    }
}
