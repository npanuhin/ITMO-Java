package expression;

import java.math.BigDecimal;
import java.util.Objects;


public class Minus extends AbstractUnaryOperator {
    public Minus(AbstractExpression content) {
        super(content);
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    protected int count(int a) {
        return -a;
    }

    @Override
    protected BigDecimal count(BigDecimal a) {
        return a.negate();
    }
}
