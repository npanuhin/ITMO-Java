package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // System.out.print("Enter m, n and k in one line (e.g. \"5 7 3\"): ");
        // Scanner input = new Scanner(System.in);
        // int m = input.nextInt();
        // int n = input.nextInt();
        // int k = input.nextInt();

        int m = 11;
        int n = 11;
        int k = 5;

        System.out.println(String.format("Playing game %s,%s,%s", m, n, k));

        final int result = new TwoPlayerGame(
                // new MNKBoard(m, n, k),
                new MNKHexBoard(n, k),

                new RandomPlayer(),
                new RandomPlayer()
                // new SequentialPlayer()
                // new CheatingPlayer()
                // new HumanPlayer(new Scanner(System.in))
        ).play(true);

        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown match result " + result);
        }
    }
}
