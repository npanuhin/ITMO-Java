import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ReverseHexAbc2 {
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
        //     System.err.println(Arrays.toString(data.get(i).numbers));
        // }


        // Output:

        StringBuilder result = new StringBuilder();
        char[] stack = new char[10];

        for (int lineIndex = data.size() - 1; lineIndex >= 0; lineIndex--) {

            for (int numberIndex = data.get(lineIndex).size - 1; numberIndex >= 0; numberIndex--) {

                int number = data.get(lineIndex).numbers[numberIndex];

                if (number < 0) {
                    result.append('-');
                    // System.out.print('-');
                    number *= -1;
                }

                if (number == 0) {
                    result.append('a');
                    // System.out.print('a');
                } else {
                    int digitIndex = 0;
                    while (number != 0) {
                        stack[digitIndex++] = (char) ('a' + number % 10);
                        number /= 10;
                    }

                    for (digitIndex--; digitIndex >= 0; digitIndex--) {
                        result.append(stack[digitIndex]);
                        // System.out.print(stack[digitIndex]);
                    }
                }

                // =============================================================

                // int digitIndex = 0;

                // if (data.get(lineIndex).numbers[numberIndex] < 0) {
                //     result.append('-');
                //     // System.out.print('-');
                //     digitIndex++;
                // }

                // String number = Integer.toString(data.get(lineIndex).numbers[numberIndex]);

                // for (; digitIndex < number.length(); digitIndex++) {
                //     result.append((char) ('a' + number.charAt(digitIndex) - '0'));
                //     // System.out.print((char) ('a' + number.charAt(digitIndex) - '0'));
                // }

                result.append(' ');
                // System.out.print(' ');
            }

            result.append('\n');
            // System.out.println();
        }

        System.out.print(result);
    }
}
