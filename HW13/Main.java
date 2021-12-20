import expression.exceptions.ExpressionParser;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();

        // System.out.println(Integer.MIN_VALUE);
        // System.out.println(Integer.MAX_VALUE);

        // System.out.println(parser.parse(Integer.toString(0) + " + " + Integer.toString(Integer.MAX_VALUE)).evaluate(0));

        System.out.println(parser.parse("a").toString());
    }
}
