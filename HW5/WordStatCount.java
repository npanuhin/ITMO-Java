import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;


class WordStatDelimiter implements Delimiter {
    public boolean isWhitespace(char c) {
        return !(
            Character.getType(c) == Character.DASH_PUNCTUATION ||
            Character.isLetter(c) ||
            c == '\''
        );
    }
}

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

            AbcScanner input = new AbcScanner(new FileInputStream(args[0]), new WordStatDelimiter());

            try {
                
                while (input.canRead()) {
                    while (input.hasNextInLine()) {
                        String word = input.nextString().toLowerCase();
                        count.put(word, count.getOrDefault(word, 0) + 1);
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
