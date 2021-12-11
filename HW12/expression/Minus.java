package expression;

import java.math.BigDecimal;
import java.util.Objects;


public class Minus implements AbstractExpression {
    private final AbstractExpression content;

    public Minus(AbstractExpression content) {
        this.content = content;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Associativity is not defined for Minus");
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return false;
    }

    @Override
    public String toString() {
        return "-(" + content.toString() + ')';
    }

    @Override
    public String toMiniString() {
        if (this.getPriority() > content.getPriority()) {
            return "-(" + content.toMiniString() + ")";
        } else {
            return "- " + content.toMiniString();
        }
    }

    @Override
    public int evaluate(int x) {
        return -content.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -content.evaluate(x, y, z);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return content.evaluate(x).negate();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return content.equals(((Minus) obj).content);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getClass()) + 17 * content.hashCode();
    }
}
