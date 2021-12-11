package expression.parser;

public interface AbstractStreamer {
    public char curChar();
    public char getChar();
    public boolean hasNextChar();

    public void expectChar(char c);
    public boolean skipIfMatch(char c);
}
