package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    protected int count(int a, int b) {
        return a / b;
    }
}