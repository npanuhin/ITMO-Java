package expression;

import java.math.BigDecimal;
import java.util.Objects;


public class Negate extends AbstractUnaryOperator {
    public Negate(AbstractExpression content) {
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
