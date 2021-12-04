package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 1;
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
        return "-";
    }

    @Override
    protected int count(int a, int b) {
        return a - b;
    }
}
