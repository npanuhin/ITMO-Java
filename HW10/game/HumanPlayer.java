package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner input;

    public HumanPlayer(Scanner input) {
        this.input = input;
    }

    @Override
    public Move makeMove(final ProtectedPosition position) {
        Move move;
        System.out.println();
        System.out.println("Current position:");
        System.out.println(position);

        asking:
        while (true) {
            System.out.print(
                "Enter you move for " + position.getTurn() + " (you can also `surrender` or offer a `draw`): "
            );
            String line = input.nextLine().trim().toLowerCase();
            if (line.isEmpty()) {
                continue;
            }

            switch (line) {
                case "surrender":
                    move = new Surrender();
                    break asking;
                case "draw":
                    move = new DrawOffer();
                    break asking;
                default:
                    try {
                        String[] splittedLine = line.split("\\s+");
                        if (splittedLine.length != 2) {
                            System.out.println("Invalid number of arguments: input must be 2 integers");
                            continue;
                        }
                        move = new Move(
                            Integer.parseInt(splittedLine[0]) - 1,
                            Integer.parseInt(splittedLine[1]) - 1,
                            position.getTurn()
                        );

                        if (position.isValid(move)){
                            break asking;
                        } else {
                            System.out.println("Invalid position!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid format!");
                    }
            }
        }
        return move;
    }

    @Override
    public boolean askForDraw(final String appeal) {
        while (true) {
            System.out.print(appeal + ", do you want to accept the draw offer? (yes/no): ");
            String line = input.nextLine().trim().toLowerCase();
            switch (line) {
                case "yes": return true;
                case "no": return false;
                default: System.out.println("Response should be either `yes` or `no`.");
            }
        }
    }
}
