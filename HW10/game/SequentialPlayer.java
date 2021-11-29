package game;

public class SequentialPlayer implements Player {
    @Override
    public Move makeMove(ProtectedPosition position) {
        for (int r = 0; r < position.getN(); r++) {
            for (int c = 0; c < position.getM(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }

    @Override
    public boolean askForDraw(String appeal) {
        return true;
    }
}
