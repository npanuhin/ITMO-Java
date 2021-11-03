import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.io.FileNotFoundException;


public class Reverse {
    public static void main(String[] args) {

        List<IntList> data = new ArrayList<IntList>();

        // Input:
        try {
            AbcScanner fileScanner = new AbcScanner(System.in);

            try {
                while (fileScanner.canRead()) {
                    IntList line = new IntList();
                    data.add(line);

                    int itemIndex = 0;
                    while (fileScanner.hasNextInLine()) {
                        line.set(itemIndex++, fileScanner.nextInt());
                    }

                    fileScanner.skipLine();
                }

            } finally {
                fileScanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Cannot read input file: " + e.getMessage());
        }

        // for (int i = 0; i < data.size(); i++) {
        //     System.out.println(Arrays.toString(data.get(i).numbers));
        // }


        // Output:

        StringBuilder result = new StringBuilder();

        for (int lineIndex = data.size() - 1; lineIndex >= 0; lineIndex--) {

            for (int numberIndex = data.get(lineIndex).size - 1; numberIndex >= 0; numberIndex--) {
                result.append(data.get(lineIndex).numbers[numberIndex]);
                result.append(' ');
                // System.out.print(data.get(lineIndex).numbers[numberIndex]);
                // System.out.print(' ');
            }

            result.append('\n');
            // System.out.println();
        }

        System.out.print(result);
    }
}
