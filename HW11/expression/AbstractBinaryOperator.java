package expression;

import java.math.BigDecimal;
import java.util.Objects;


public abstract class AbstractBinaryOperator implements AbstractExpression {
    protected final AbstractExpression left, right;
    private String cachedToString = null, cachedToMiniString = null;

    public AbstractBinaryOperator(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getOperator();
    protected abstract int count(int left, int right);
    protected abstract BigDecimal count(BigDecimal left, BigDecimal right);

    @Override
    public int evaluate(int x) {
        return count(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return count(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append('(');
        left.toString(sb);
        sb.append(' ').append(getOperator()).append(' ');
        right.toString(sb);
        sb.append(')');
    }

    @Override
    public String toString() {
        if (cachedToString == null) {
            StringBuilder result = new StringBuilder();
            toString(result);
            cachedToString = result.toString();
        }
        return cachedToString;
    }

    @Override
    public void toMiniString(StringBuilder sb) {
        // Adding left operand
        addMiniExprToBuilder(sb, left, (this.getPriority() > left.getPriority()));

        // Adding operator
        sb.append(' ').append(getOperator()).append(' ');

        // Adding right operand
        if (this.getPriority() < right.getPriority()) {
            addMiniExprToBuilder(sb, right, false);

        } else if (this.alwaysNeedsWrap() || right.alwaysNeedsWrap()) {
            addMiniExprToBuilder(sb, right, true);

        } else if (this.getPriority() == right.getPriority()) {
            addMiniExprToBuilder(sb, right, !this.isAssociative());

        } else {
            addMiniExprToBuilder(sb, right, true);
        }
    }

    @Override
    public String toMiniString() {
        if (cachedToMiniString == null) {
            StringBuilder result = new StringBuilder();
            toMiniString(result);
            cachedToMiniString = result.toString();
        }
        return cachedToMiniString;
    }

    private void addMiniExprToBuilder(StringBuilder builder, AbstractExpression expr, boolean isWrapped) {
        if (isWrapped) {
            builder.append('(');
        }
        expr.toMiniString(builder);
        if (isWrapped) {
            builder.append(')');
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            return left.equals(((AbstractBinaryOperator) obj).left)
                && right.equals(((AbstractBinaryOperator) obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getClass()) + 17 * (left.hashCode() + 17 * right.hashCode());
    }
}
