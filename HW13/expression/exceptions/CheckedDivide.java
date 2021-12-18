package expression.exceptions;

import java.math.BigDecimal;

import expression.*;


public class CheckedDivide extends AbstractCheckedBinaryOperator {
    public CheckedDivide(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    protected int count(int a, int b) throws OverflowException {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(String.format("%d / %d", a, b));
        }
        return a / b;
    }

    @Override
    protected BigDecimal count(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }
}
