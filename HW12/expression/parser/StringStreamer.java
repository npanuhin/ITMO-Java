package expression.parser;

public class StringStreamer implements AbstractStreamer {
    private final String content;
    private int pos = 0;

    public StringStreamer(String content) {
        this.content = content;
    }

    @Override
    public char curChar() {
        return content.charAt(pos);
    }

    @Override
    public char getChar() {
        return content.charAt(pos++);
    }

    @Override
    public void expectChar(char c) {
        if (getChar() != c) {
            throw new IllegalArgumentException("Expected \"" + c + "\", got smth else");
        }
    }

    @Override
    public boolean hasNextChar() {
        return pos < content.length();
    }

    @Override
    public boolean skipIfMatch(char c) {
        if (content.charAt(pos) == c) {
            pos++;
            return true;
        }
        return false;
    }
}
