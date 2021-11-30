package game;

import java.util.ArrayList;
import java.util.Arrays;


public class MNKHexBoard extends MNKBoard {

    public MNKHexBoard(int n, int k) {
        super(n, n, k);
    }

    protected static final ArrayList<PairIntInt> ONE_WAY_DIRECTIONS = new ArrayList<>(Arrays.asList(
        new PairIntInt(1, 0),
        new PairIntInt(0, 1),
        new PairIntInt(-1, 1)
    ));

    @Override
    protected boolean checkWin(final Move move) {
        return checkWin(move, ONE_WAY_DIRECTIONS);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(n + 2)).append(1).append(" ").append(1).append(System.lineSeparator());

        for (int row = 0; row < n; row++) {
            sb.append(" ".repeat(n - row));

            if (row < n - 1) {
                sb.append(row + 2 < 10 ? " " : "").append(row + 2).append(" ");
            } else {
                sb.append(" ".repeat(3));
            }

            for (int column = 0; column <= row; column++) {
                sb.append(CELL_TO_STRING.get(field[row - column][column])).append(" ");
            }

            if (row < n - 1) {
                sb.append(row + 2);
            }

            sb.append(System.lineSeparator());
        }

        for (int row = n - 2; row >= 0; row--) {
            sb.append(" ".repeat(n - row + 3));
            for (int column = 0; column <= row; column++) {
                sb.append(CELL_TO_STRING.get(field[n - column - 1][n - row + column - 1])).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
