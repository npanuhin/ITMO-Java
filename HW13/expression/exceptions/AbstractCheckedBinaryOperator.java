package expression.exceptions;

import expression.*;


public abstract class AbstractCheckedBinaryOperator extends AbstractBinaryOperator {

    public AbstractCheckedBinaryOperator(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    protected abstract int count(int left, int right) throws OverflowException;

    @Override
    public int evaluate(int x) throws OverflowException {
        return count(left.evaluate(x), right.evaluate(x));
    }
}
