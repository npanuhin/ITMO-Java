import expression.*;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        // Subtract result = new Subtract(
        //     new Multiply(
        //         new Const(2),
        //         new Variable("x")
        //     ),
        //     new Const(3)
        // );

        // System.out.println(result.evaluate(5));
        // System.out.println(result);

        // System.out.println(new Multiply(new Const(2), new Variable("x")).equals(new Multiply(new Const(2), new Variable("x"))));
        // System.out.println(new Multiply(new Const(2), new Variable("x")).equals(new Multiply(new Variable("x"), new Const(2))));

        // Variable vx = new Variable("x"), vy = new Variable("y"), vz = new Variable("z");

        // Const c1 = new Const(1), c2 = new Const(2);

        // Multiply test1 = new Multiply(new Subtract(new Variable("y"), new Variable("x")), new Divide(new Const(2), new Variable("x")));
        // Divide test2 = new Divide(new Subtract(new Variable("y"), new Variable("x")), new Multiply(new Const(2), new Variable("x")));

        // System.out.println(test1.hashCode());
        // System.out.println();
        // System.out.println(test2.hashCode());

        Variable bigDecimalVariable = new Variable("x");
        Const bigDecimalConst = new Const(new BigDecimal(10));

        // System.out.println(bigDecimalVariable.evaluate(new BigDecimal(10)));
        // System.out.println(bigDecimalConst.evaluate(new BigDecimal(10)));

        System.out.println(bigDecimalVariable.toString());
        System.out.println(bigDecimalConst.toString());

        Add task = new Add(
            new Subtract(
                new Multiply(
                    new Variable("x"),
                    new Variable("x")
                ),
                new Multiply(
                    new Const(2),
                    new Variable("x")
                )
            ),
            new Const(1)
        );

        System.out.println(task.evaluate(5));
    }
}
