package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return false;
    }

    @Override
    protected String getOperator() {
        return "*";
    }

    @Override
    protected int count(int a, int b) {
        return a * b;
    }
}