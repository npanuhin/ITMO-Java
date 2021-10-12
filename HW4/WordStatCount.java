import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;


public class WordStatCount {

    static class PairStringInt implements Comparable <PairStringInt> {
        public String first;
        public int second;

        public PairStringInt(String first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(PairStringInt other) {
            return this.second - other.second;
        }
    }


    public static void main(String[] args) {

        Map<String, Integer> count = new LinkedHashMap<>();

        // Input:
        try {

            BufferedReader input = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            );

            try {
                StringBuilder builder = new StringBuilder();
                int read;

                do {
                    read = input.read();

                    char character = Character.toLowerCase((char) read);

                    if (
                        read != -1 && 
                        (
                            Character.getType(character) == Character.DASH_PUNCTUATION ||
                            Character.isLetter(character) ||
                            character == '\''
                        )
                    ) {
                        builder.append(character);

                    } else if (builder.length() != 0) {
                        String word = builder.toString();
                        count.put(word, count.getOrDefault(word, 0) + 1);
                        builder.setLength(0);
                    }

                } while (read != -1);

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

                PairStringInt[] mapData = new PairStringInt[count.size()];

                int index = 0;
                for (var entry : count.entrySet()) {
                    mapData[index++] = new PairStringInt(entry.getKey(), entry.getValue());
                }

                Arrays.sort(mapData);

                for (int i = 0; i < mapData.length; i++) {
                    output.write(mapData[i].first);
                    output.write(' ');
                    output.write(String.valueOf(mapData[i].second));
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
