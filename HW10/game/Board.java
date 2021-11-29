package game;

public interface Board {
    ProtectedPosition getProtectedPosition();

    GameResult makeMove(Move move);
}
