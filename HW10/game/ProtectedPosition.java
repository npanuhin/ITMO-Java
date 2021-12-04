package game;

public class ProtectedPosition implements Position {
    private Position position;

    public ProtectedPosition(final Position position) {
        this.position = position;
    }

    @Override
    public int getM() {
        return position.getM();
    }

    @Override
    public int getN() {
        return position.getN();
    }

    @Override
    public int getK() {
        return position.getK();
    }

    @Override
    public Cell getCell(final int row, final int column) {
        return position.getCell(row, column);
    }

    @Override
    public Cell getTurn() {
        return position.getTurn();
    }

    @Override
    public boolean isValid(final Move move) {
        return position.isValid(move);
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
