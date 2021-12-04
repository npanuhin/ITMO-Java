package expression;

import java.util.Objects;


public abstract class AbstractBinaryOperator implements AbstractExpression {
    protected final AbstractExpression left, right;

    public AbstractBinaryOperator(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getOperator();
    protected abstract int count(int left, int right);

    @Override
    final public int evaluate(int x) {
        return count(left.evaluate(x), right.evaluate(x));
    }

    @Override
    final public int evaluate(int x, int y, int z) {
        return count(left.evaluate(x, y, z), right.evaluate(x, y, z));
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

        addExprToBuilder(result, left.toMiniString(), (this.getPriority() > left.getPriority()));

        result.append(' ').append(getOperator()).append(' ');

        if (this.getPriority() < right.getPriority()) {
            addExprToBuilder(result, right.toMiniString(), false);

        } else if (this.alwaysNeedsWrap() || right.alwaysNeedsWrap()) {
            addExprToBuilder(result, right.toMiniString(), true);

        } else if (this.getPriority() == right.getPriority()) {
            addExprToBuilder(result, right.toMiniString(), !this.isAssociative());

        } else {
            addExprToBuilder(result, right.toMiniString(), true);
        }

        return result.toString();
    }

    private void addExprToBuilder(StringBuilder builder, String item, boolean isWrapped) {
        if (isWrapped) {
            builder.append('(').append(item).append(')');
        } else {
            builder.append(item);
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
        return Objects.hashCode(this.getClass()) + 31 * (left.hashCode() + 17 * right.hashCode());
    }
}
