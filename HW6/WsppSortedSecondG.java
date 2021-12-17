import java.util.HashMap;
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

import myclasses.*;


class WsppSortedSecongGDelimiter implements Scanner.Delimiter {
    public boolean isWhitespace(char c) {
        return !(
            Character.getType(c) == Character.DASH_PUNCTUATION ||
            Character.isLetter(c) ||
            c == '\''
        );
    }
}

public class WsppSortedSecondG {

    static class PairStringWsppWord implements Comparable <PairStringWsppWord> {
        public String first;
        public WsppWord second;

        public PairStringWsppWord(String first, WsppWord second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(PairStringWsppWord other) {
            return this.first.compareTo(other.first);
        }
    }

    static class WsppWord {
        public int count = 0;
        public int countInLine = 0;
        public IntList list = new IntList();
    }


    public static void main(String[] args) {

        Map<String, WsppWord> data = new HashMap<>();

        // Input:
        try {
            Scanner input = new Scanner(new FileInputStream(args[0]), new WsppSortedSecongGDelimiter());

            try {

                int wordIndex = 0;
                while (input.canRead()) {

                    while (input.hasNextInLine()) {
                        String word = input.nextString().toLowerCase();

                        WsppWord cur = data.computeIfAbsent(word, s -> new WsppWord());

                        if (cur.countInLine % 2 == 1) {
                            cur.list.add(wordIndex);
                        }
                        cur.countInLine++;
                        wordIndex++;
                    }

                    for (WsppWord word : data.values()) {
                        word.count += word.countInLine;
                        word.countInLine = 0;
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

        // System.err.println(count);

        // Output:
        try {

            BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf-8"
                )
            );

            try {

                PairStringWsppWord[] mapData = new PairStringWsppWord[data.size()];

                int index = 0;
                for (var entry : data.entrySet()) {
                    mapData[index++] = new PairStringWsppWord(entry.getKey(), entry.getValue());
                }

                Arrays.sort(mapData);

                for (int i = 0; i < mapData.length; i++) {
                    output.write(mapData[i].first);
                    output.write(' ');
                    output.write(Integer.valueOf(mapData[i].second.count).toString());

                    IntList positions = mapData[i].second.list;

                    for (int j = 0; j < positions.size; j++) {
                        output.write(' ');
                        output.write(Integer.valueOf(positions.numbers[j] + 1).toString());
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
