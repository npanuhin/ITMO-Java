package expression;

public class Const implements AbstractExpression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("isAssociative is not defined for Const");
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return value == ((Const) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
