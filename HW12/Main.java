import expression.AbstractExpression;
import expression.parser.ExpressionParser;

import java.io.IOException;

import myclasses.Scanner;


public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();

        try {
            Scanner scanner = new Scanner(System.in);

            AbstractExpression task = parser.parse(scanner.nextLine());
            scanner.skipLine();
            System.out.println(task.evaluate(scanner.nextInt()));

        } catch (IOException e) {
            System.out.println("Cannot open input stream: " + e.getMessage());
        }
    }
}
