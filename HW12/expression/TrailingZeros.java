package expression;

import java.math.BigDecimal;
import java.util.Objects;


public class TrailingZeros extends AbstractUnaryOperator {
    public TrailingZeros(AbstractExpression content) {
        super(content);
    }

    @Override
    public String getOperator() {
        return "t0";
    }

    @Override
    protected int count(int a) {
        return Integer.numberOfTrailingZeros(a);
    }

    @Override
    protected BigDecimal count(BigDecimal a) {
        throw new UnsupportedOperationException("number of trailing zeros is not defined for BigDecimal");
    }
}
