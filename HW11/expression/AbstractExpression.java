package expression;

public interface AbstractExpression extends Expression, TripleExpression, BigDecimalExpression {
    public int getPriority();
    public boolean isAssociative();
    public boolean alwaysNeedsWrap();

    default void toString(StringBuilder sb) {
        sb.append(toString());
    }

    default void toMiniString(StringBuilder sb) {
        sb.append(toString());
    }
}
