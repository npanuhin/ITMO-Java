package game;

public interface Player {
    Move makeMove(ProtectedPosition position);
    boolean askForDraw(String appeal);
}
