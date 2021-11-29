package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(ProtectedPosition position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(position.getN()),
                    random.nextInt(position.getM()),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public boolean askForDraw(String appeal) {
        return random.nextBoolean();
    }
}
