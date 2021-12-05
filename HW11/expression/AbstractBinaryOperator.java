package expression;

import java.math.BigDecimal;
import java.util.Objects;


public abstract class AbstractBinaryOperator implements AbstractExpression {
    protected final AbstractExpression left, right;

    public AbstractBinaryOperator(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getOperator();
    protected abstract int count(int left, int right);
    protected abstract BigDecimal count(BigDecimal left, BigDecimal right);

    @Override
    final public int evaluate(int x) {
        return count(left.evaluate(x), right.evaluate(x));
    }

    @Override
    final public int evaluate(int x, int y, int z) {
        return count(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    final public BigDecimal evaluate(BigDecimal x) {
        return count(left.evaluate(x), right.evaluate(x));
    }

    @Override
    final public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('(').append(left).append(' ').append(getOperator()).append(' ').append(right).append(')');
        return result.toString();
    }

    @Override
    final public String toMiniString() {
        StringBuilder result = new StringBuilder();

        // Adding left operand
        addMiniExprToBuilder(result, left, (this.getPriority() > left.getPriority()));

        // Adding operator
        result.append(' ').append(getOperator()).append(' ');

        // Adding right operand
        if (this.getPriority() < right.getPriority()) {
            addMiniExprToBuilder(result, right, false);

        } else if (this.alwaysNeedsWrap() || right.alwaysNeedsWrap()) {
            addMiniExprToBuilder(result, right, true);

        } else if (this.getPriority() == right.getPriority()) {
            addMiniExprToBuilder(result, right, !this.isAssociative());

        } else {
            addMiniExprToBuilder(result, right, true);
        }

        return result.toString();
    }

    private void addMiniExprToBuilder(StringBuilder builder, AbstractExpression expr, boolean isWrapped) {
        if (isWrapped) {
            builder.append('(').append(expr.toMiniString()).append(')');
        } else {
            builder.append(expr.toMiniString());
        }
    }

    @Override
    final public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            return left.equals(((AbstractBinaryOperator) obj).left)
                && right.equals(((AbstractBinaryOperator) obj).right);
        }
        return false;
    }

    @Override
    final public int hashCode() {
        return Objects.hashCode(this.getClass()) + 17 * (left.hashCode() + 31 * right.hashCode());
    }
}
