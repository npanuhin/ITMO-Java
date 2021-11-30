package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class MNKBoard implements Board, Position {
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
        Cell.E, ".",
        Cell.X, "X",
        Cell.O, "O"
    );

    private static final ArrayList<PairIntInt> ONE_WAY_DIRECTIONS = new ArrayList<>(Arrays.asList(
        new PairIntInt(1, 0),
        new PairIntInt(0, 1),
        new PairIntInt(1, 1),
        new PairIntInt(1, -1)
    ));

    protected final int m, n, k;
    private int emptyCells;

    protected final Cell[][] field;
    private Cell turn;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        this.emptyCells = m * n;

        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public ProtectedPosition getProtectedPosition() {
        return new ProtectedPosition(this);
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public Cell getCell(final int row, final int column) {
        return field[row][column];
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public GameResult makeMove(final Move move) {
        if (!isValid(move)) {
            System.out.println("Player made invalid move");
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        return --emptyCells == 0;
    }

    protected boolean checkWin(final Move move) {
        return checkWin(move, ONE_WAY_DIRECTIONS);
    }

    protected boolean checkWin(final Move move, ArrayList<PairIntInt> directions) {
        for (PairIntInt one_way_direction : directions) {
            if (
                countInDirection(move, one_way_direction.first, one_way_direction.second) +
                countInDirection(move, -one_way_direction.first, -one_way_direction.second) - 1 >= k
            ) {
                return true;
            }
        }
        return false;
    }

    private int countInDirection(final Move move, final int deltaRow, final int deltaCol) {
        int count = 0;
        int curRow = move.getRow();
        int curCol = move.getCol();

        while (
            0 <= curRow && curRow < n &&
            0 <= curCol && curCol < m &&
            field[curRow][curCol] == move.getValue() &&
            count <= k
        ) {
            count++;
            curRow += deltaRow;
            curCol += deltaCol;
        }

        return count;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && move.getValue() == turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        if (n > 10) {
            sb.append(' ');
        }
        for (int i = 0; i < m; ++i) {
            sb.append(' ').append(i + 1);
        }
        sb.append(System.lineSeparator());
        for (int row = 0; row < n; row++) {
            sb.append(row + 1).append(' ');
            if (row < 10) {
                sb.append(' ');
            }
            for (Cell cell : field[row]) {
                sb.append(CELL_TO_STRING.get(cell));
                sb.append(' ');
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
