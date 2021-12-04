package expression;

public class Add extends AbstractBinaryOperator {
    public Add(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 1;
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
        return "+";
    }

    @Override
    protected int count(int a, int b) {
        return a + b;
    }
}
