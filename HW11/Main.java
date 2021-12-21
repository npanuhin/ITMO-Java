import expression.*;

import java.io.IOException;

import myclasses.Scanner;


public class Main {
    public static void main(String[] args) {
        final Add task = new Add(
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

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println(task.evaluate(scanner.nextInt()));

        } catch (IOException e) {
            System.out.println("Cannot open input stream: " + e.getMessage());
        }
    }
}
