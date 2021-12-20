package expression.exceptions;

import expression.*;


public abstract class AbstractCheckedUnaryOperator extends AbstractUnaryOperator {

    public AbstractCheckedUnaryOperator(AbstractExpression content) {
        super(content);
    }

    protected abstract int count(int content) throws OverflowException;

    @Override
    public int evaluate(int x) throws OverflowException {
        return count(content.evaluate(x));
    }
}
