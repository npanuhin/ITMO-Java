package expression;

import java.math.BigDecimal;


public class LeftBitshift extends AbstractBinaryOperator {
    public LeftBitshift(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 0;
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
        return "<<";
    }

    @Override
    protected int count(int a, int b) {
        return a << b;
    }

    @Override
    protected BigDecimal count(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("Bitshift is not defined for BigDecimal");
    }
}
