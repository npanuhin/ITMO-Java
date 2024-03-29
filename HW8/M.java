import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;

import java.io.Serializable;

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

class IntList implements Serializable {
    public int[] numbers = new int[0];
    public int size = 0;

    public void set(int pos, int value) {
        if (pos >= this.numbers.length) {
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



public class M {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        int t = input.nextInt();

        for (int req = 0; req < t; req++) {

            // Input:
            input.skipLine();
            int n = input.nextInt();
            input.skipLine();

            IntList difficulties = new IntList();
            for (int i = 0; i < n; i++) {
                difficulties.add(input.nextInt());
            }

            // Solution & output:

            int answer = 0;
            Map<Integer, Integer> countK = new HashMap<>();

            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    answer += countK.getOrDefault(2 * difficulties.get(j) - difficulties.get(i), 0);
                }
                countK.put(difficulties.get(j), countK.getOrDefault(difficulties.get(j), 0) + 1);
            }

            output.write(Integer.valueOf(answer).toString());
            output.newLine();
        }

        output.close();
    }
}
