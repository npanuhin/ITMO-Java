import java.util.Arrays;
import java.util.ArrayList;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.Reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;


interface Delimiter {
    public boolean isWhitespace(char c);
}

class DefaultDelimiter implements Delimiter {
    public boolean isWhitespace(char c) {
        return c == ' ';
    }
}

class Scanner implements AutoCloseable {
    private Reader reader;
    private int read;
    private Delimiter delimiter = new DefaultDelimiter();

    private char[] buffer = new char[1024];  // 1024, 1MB = 1048576
    private int bufferPos = 0;
    private int storedInBuffer = 0;

    private boolean hasBufferedChar = false;
    private char bufferedChar;

    private char[] lineSeparators = new char[] {'\n', '\r', '\u000B', '\u000C', '\u0085', '\u2028', '\u2029'};


    public Scanner(InputStreamReader reader) {
        this.reader = reader;
    }
    public Scanner(InputStreamReader reader, Delimiter delimiter) {
        this.reader = reader;
        this.delimiter = delimiter;
    }
    public Scanner(InputStream in) throws IOException {
        this(new InputStreamReader(in, "utf-8"));
    }
    public Scanner(InputStream in, Delimiter delimiter) throws IOException {
        this(new InputStreamReader(in, "utf-8"), delimiter);
    }


    public Scanner(StringReader in) throws IOException {
        this.reader = in;
    }
    public Scanner(StringReader in, Delimiter delimiter) throws IOException {
        this.reader = in;
        this.delimiter = delimiter;
    }
    public Scanner(String in) throws IOException {
        this(new StringReader(in));
    }
    public Scanner(String in, Delimiter delimiter) throws IOException {
        this(new StringReader(in), delimiter);
    }



    public void close() throws IOException {
        reader.close();
    }

    // =============================================================================

    private boolean read() throws IOException {
        if (!hasBufferedChar) {
            while (bufferPos == storedInBuffer) {
                if ((storedInBuffer = reader.read(buffer)) == -1) {
                    return false;
                }
                bufferPos = 0;
            }
            bufferedChar = buffer[bufferPos++];
            hasBufferedChar = true;
        }
        return true;
    }

    public boolean canRead() throws IOException {
        return hasBufferedChar || read();
    }

    // =============================================================================

    // private boolean isWhitespace() {
    //     return delimiter.isWhitespace(bufferedChar);
    // }

    private boolean isEndOfLine() {
        for (char testSeparator : lineSeparators) {
            if (testSeparator == bufferedChar) {
                return true;
            }
        }
        return false;
    }

    private boolean isDigit() {
        return Character.isDigit(bufferedChar);
    }

    private void skipWhitespaces() throws IOException {
        while (canRead() && delimiter.isWhitespace(bufferedChar) && !isEndOfLine()) {
            hasBufferedChar = false;
        }
    }

    // private void skipLinesAndWhitespaces() throws IOException {
    //     while (canRead() && (delimiter.isWhitespace(bufferedChar) || isEndOfLine())) {
    //         hasBufferedChar = false;
    //     }
    // }

    // =============================================================================

    public boolean hasNextInLine() throws IOException {
        skipWhitespaces();
        return hasBufferedChar && !isEndOfLine();
    }

    public void skipLine() throws IOException {
        while (canRead() && !isEndOfLine()) {
            hasBufferedChar = false;
        }
        hasBufferedChar = false;

        if (
            (bufferedChar == '\r' && canRead() && bufferedChar == '\n') || 
            (bufferedChar == '\n' && canRead() && bufferedChar == '\r')
        ) {
            hasBufferedChar = false;
        }
    }

    // =============================================================================

    public String nextString() throws IOException {
        skipWhitespaces();

        // if (isEndOfLine()) {
        //     throw new IOException("Empty input to generate String"); 
        // }

        StringBuilder result = new StringBuilder();

        while (canRead() && !delimiter.isWhitespace(bufferedChar) && !isEndOfLine()) {
            result.append(bufferedChar);
            hasBufferedChar = false;
        }

        return result.toString();
    }

    public int nextInt() throws IOException {
        String number = nextString();

        if (number.startsWith("0x") || number.startsWith("0X")) {
            return Integer.parseInt(number.substring(2), 16);
        } else {
            return Integer.parseInt(number);
        }
    }
}

class IntList {
    public int[] numbers = new int[0];
    public int size = 0;

    public void set(int pos, int value) {
        while (pos >= this.numbers.length) {
            this.numbers = Arrays.copyOf(this.numbers, Math.max(this.numbers.length * 2, 1));
        }
        this.numbers[pos] = value;
        this.size = Math.max(this.size, pos + 1);
    }

    public void add(int value) {
        set(size, value);
    }

    public int get(int pos) {
        return this.numbers[pos];
    }
}


class Obelisk {
    int x, y, height;

    Obelisk(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}


public class I {
    public static void main(String[] args) throws IOException {

        // Input:
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        ArrayList<Obelisk> obelisks = new ArrayList<>();

        int x, y, height;
        for (int i = 0; i < n; i++) {
            while (!input.hasNextInLine()) {
                input.skipLine();
            }

            x = input.nextInt();
            y = input.nextInt();
            height = input.nextInt();

            obelisks.add(new Obelisk(x, y, height));
        }


        // Solution:
        int mainSquareLeftBound = obelisks.get(0).x - obelisks.get(0).height,
            mainSquareRightBound = obelisks.get(0).x + obelisks.get(0).height,

            mainSquareBottomBound = obelisks.get(0).y - obelisks.get(0).height,
            mainSquareTopBound = obelisks.get(0).y + obelisks.get(0).height;

        for (Obelisk obelisk : obelisks) {
            mainSquareLeftBound = Math.min(mainSquareLeftBound, obelisk.x - obelisk.height);
            mainSquareRightBound = Math.max(mainSquareRightBound, obelisk.x + obelisk.height);

            mainSquareBottomBound = Math.min(mainSquareBottomBound, obelisk.y - obelisk.height);
            mainSquareTopBound = Math.max(mainSquareTopBound, obelisk.y + obelisk.height);
        }

        int resultHeight = (int) Math.ceil((double) (Math.max(
            mainSquareRightBound - mainSquareLeftBound,
            mainSquareTopBound - mainSquareBottomBound
        )) / 2);

        int resultX = mainSquareLeftBound + resultHeight;
        int resultY = mainSquareBottomBound + resultHeight;


        // Output:
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        output.write(Integer.valueOf(resultX).toString());
        output.write(' ');
        output.write(Integer.valueOf(resultY).toString());
        output.write(' ');
        output.write(Integer.valueOf(resultHeight).toString());
        output.newLine();

        output.close();
    }
}