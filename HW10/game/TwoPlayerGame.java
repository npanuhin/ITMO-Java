package game;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public TwoPlayerGame(final Board board, final Player player1, final Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeTwoPlayerMove(player1, 1, player2, log);
            if (result1 != -1) {
                return result1;
            }

            final int result2 = makeTwoPlayerMove(player2, 2, player1, log);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int makeTwoPlayerMove(
        final Player cur_player,
        final int cur_player_no,
        final Player other_player,
        final boolean log
    ) {
        boolean drawOffered = false;
        int result = 3;
        while (result >= 3) {
            result = makeMove(cur_player, cur_player_no, log);

            if (result == 3) {
                System.out.println("Player " + cur_player_no + " chose to surrender");
                return 3 - cur_player_no;
            }

            if (result == 4) {
                if (drawOffered) {
                    System.out.println("You can no longer offer a draw this turn");
                    continue;
                }
                drawOffered = true;
                boolean drawOfferResult = other_player.askForDraw("Player " + (3 - cur_player_no));
                System.out.println(
                    "Player " + (3 - cur_player_no) + " " + (drawOfferResult ? "accepted" : "declined") + " a draw offer"
                );
                if (drawOfferResult) {
                    return 0;
                }
            }
        }
        return result;
    }

    private int makeMove(final Player player, final int no, final boolean log) {
        final Move move;
        try {
            move = player.makeMove(board.getProtectedPosition());
        } catch  (Exception e) {
            System.out.println("Exception when trying to get move from Player " + no + ": " + e.getMessage());
            return 3 - no;
        }

        if (move instanceof Surrender) {
            return 3;
        }

        if (move instanceof DrawOffer) {
            return 4;
        }

        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case LOOSE:
                return 3 - no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
