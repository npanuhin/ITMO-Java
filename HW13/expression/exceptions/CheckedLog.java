package expression.exceptions;

import expression.*;


public class CheckedLog extends Log {
    public CheckedLog(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected int count(int a, int b) {
        if (a <= 0 ) {
            throw new UnsupportedOperationException("Log(a, b) is not defined when a <= 0");
        }

        if (b <= 0 || b == 1) {
            throw new UnsupportedOperationException("Log(a, b) is not defined when b <= 0 or b = 1");
        }

        int res = 0;
        while (a >= b) {
            a /= b;
            res++;
        }
        return res;
    }
}
