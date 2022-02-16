import java.util.ArrayList;
import java.util.Arrays;

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

class IntList {
    public int[] numbers = new int[0];
    public int size = 0;

    public IntList() {}

    public IntList(int n) {
        this.numbers = Arrays.copyOf(this.numbers, n);
    }

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

    public void fill(int n, int value) {
        for (int i = 0; i < n; i++) {
            this.numbers[i] = value;
        }
        this.size = Math.max(this.size, n);
    }
}


public class H {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        // Input:
        int n = input.nextInt();
        input.skipLine();

        int maxTransation = 0;
        IntList transactionsPrefSumm = new IntList(n + 1);
        transactionsPrefSumm.set(0, 0);

        IntList transactionByQuery = new IntList(n);
        for (int i = 0; i < n; i++) {
            int transaction = input.nextInt();
            maxTransation = Math.max(maxTransation, transaction);

            transactionsPrefSumm.set(i + 1, transactionsPrefSumm.get(i) + transaction);

            for (int j = 0; j < transaction; j++) {
                transactionByQuery.add(i);
            }
        }
        input.skipLine();


        // Solution & output:
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        IntList ans = new IntList(transactionByQuery.size + 1);
        ans.fill(transactionByQuery.size + 1, -1);

        int q = input.nextInt();
        input.skipLine();

        for (int req = 0; req < q; req++) {
            int t = input.nextInt();

            if (t < maxTransation) {
                output.write("Impossible");
                output.newLine();
                continue;
            }

            if (ans.get(t) == -1) {
                int curTransaction = 0;
                int batches = 1;

                while (transactionsPrefSumm.get(curTransaction) + t < transactionByQuery.size) {
                    curTransaction = transactionByQuery.get(transactionsPrefSumm.get(curTransaction) + t);
                    batches++;
                }

                ans.set(t, batches);
            }

            output.write(Integer.valueOf(ans.get(t)).toString());
            output.newLine();
        }

        output.close();
    }
}
