package expression;

import java.math.BigDecimal;


public class LeadingZeros extends AbstractUnaryOperator {
    public LeadingZeros(AbstractExpression content) {
        super(content);
    }

    @Override
    public String getOperator() {
        return "l0";
    }

    @Override
    protected int count(int a) {
        return Integer.numberOfLeadingZeros(a);
    }

    @Override
    protected BigDecimal count(BigDecimal a) {
        throw new UnsupportedOperationException("Number of leading zeros is not defined for BigDecimal");
    }
}
