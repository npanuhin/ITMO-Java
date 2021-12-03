package game;

public class Surrender extends Move {

    public Surrender() {
        super(-1, -1, Cell.E);
    }

    @Override
    public String toString() {
        return "Surrender()";
    }
}
