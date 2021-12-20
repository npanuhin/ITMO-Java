package expression.exceptions;

import java.math.BigDecimal;

import expression.AbstractExpression;


public class CheckedPow extends AbstractCheckedBinaryOperator {
    public CheckedPow(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return false;
    }

    @Override
    protected String getOperator() {
        return "**";
    }

    @Override
    protected int count(int a, int b) {
        if (b < 0) {
            throw new UnsupportedOperationException("0 ^ x, whree x < 0 is not defined");
        }

        if (a == 0) {
            if (b == 0) {
                throw new UnsupportedOperationException("0 ^ 0 is not defined");
            } else {
                return 0;
            }
        } else if (a == 1 || b == 0) {
            return 1;
        } else if (a == -1) {
            return b % 2 == 0 ? 1 : -1;
        }

        try {
            int result = 1;
            while (b != 0) {
                if ((b & 1) == 1) {
                    CheckedMultiply.checkMultiplication(result, a);
                    result *= a;
                    b--;
                } else {
                    CheckedMultiply.checkMultiplication(a, a);
                    a *= a;
                    b >>= 1;
                }
            }
            return result;
        } catch (OverflowException e) {
            throw new OverflowException(String.format("%d ** %d", a, b));
        }
    }

    @Override
    protected BigDecimal count(BigDecimal a, BigDecimal b) {
        return a.pow(b.intValue());
    }
}
