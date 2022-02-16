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


public class WordStatInput {
    public static void main(String[] args) {

        Map<String, Integer> count = new LinkedHashMap<>();

        try {

            // Input:

            BufferedReader input = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            );

            try {
                StringBuilder builder = new StringBuilder();

                while (true) {
                    int read = input.read();

                    if (read == -1) {
                        // if (builder.length() != 0) {
                        //     String word = builder.toString();
                        //     count.put(word, count.getOrDefault(word, 0) + 1);
                        // }
                        break;
                    }

                    char character = Character.toLowerCase((char) read);

                    if (
                        Character.getType(character) == Character.DASH_PUNCTUATION ||
                        Character.isLetter(character) ||
                        character == '\''
                    ) {
                        builder.append(character);
                    } else {
                        if (builder.length() != 0) {
                            String word = builder.toString();
                            count.put(word, count.getOrDefault(word, 0) + 1);
                        }
                        builder.setLength(0);
                    }
                }

            } finally {
                input.close();
            }


            // Output:

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
                    output.write(entry.getValue().toString());
                    output.newLine();
                }

            } finally {
                output.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Cannot read or write: " + e.getMessage());
        }
    }
}
