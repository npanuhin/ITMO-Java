package expression;

public interface AbstractExpression extends Expression, TripleExpression {
    public int getPriority();
    public boolean isAssociative();
    public boolean alwaysNeedsWrap();
}
