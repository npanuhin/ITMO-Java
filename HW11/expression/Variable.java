package expression;

public class Variable implements AbstractExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("isAssociative is not defined for Variable");
    }

    @Override
    public boolean alwaysNeedsWrap() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException("No such variale found: " + name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return name.equals(((Variable) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}