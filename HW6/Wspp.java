import java.util.LinkedHashMap;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;


class WsppDelimiter implements Delimiter {
    public boolean isWhitespace(char c) {
        return !(
            Character.getType(c) == Character.DASH_PUNCTUATION ||
            Character.isLetter(c) ||
            c == '\''
        );
    }
}

public class Wspp {
    public static void main(String[] args) {

        Map<String, IntList> count = new LinkedHashMap<>();

        // Input:
        try {

            Scanner input = new Scanner(new FileInputStream(args[0]), new WsppDelimiter());

            try {
                int wordIndex = 0;
                while (input.canRead()) {
                    while (input.hasNextInLine()) {
                        count.computeIfAbsent(input.nextString().toLowerCase(), s -> new IntList()).add(wordIndex++);
                    }
                    input.skipLine();
                }

            } finally {
                input.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }

        // Output:
        try {

            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );

            try {
                for (var entry : count.entrySet()) {
                    output.write(entry.getKey());
                    output.write(' ');

                    IntList positions = entry.getValue();
                    output.write(Integer.valueOf(positions.size).toString());

                    for (int i = 0; i < positions.size; i++) {
                        output.write(' ');
                        output.write(Integer.valueOf(positions.numbers[i] + 1).toString());
                    }

                    output.newLine();
                }

            } finally {
                output.close();
            }
        
        } catch (IOException e) {
            System.out.println("Cannot write to output file: " + e.getMessage());
        }
    }
}
