import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class ReverseSum2 {
    private static class IntList {

        public int[] numbers = new int[0];
        public int size = 0;

        public void set(int pos, int value) {
            if (pos >= this.numbers.length) {
                this.numbers = Arrays.copyOf(this.numbers, Math.max(this.numbers.length * 2, 1));
            }
            this.numbers[pos] = value;
            this.size = Math.max(this.size, pos + 1);
        }
    }


    public static void main(String[] args) {

        Scanner fileScanner = new Scanner(System.in);
        List<IntList> data = new ArrayList<IntList>();

        int maxLineSize = 0;

        while (fileScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());

            IntList line = new IntList();
            data.add(line);

            int itemIndex = 0;
            while (lineScanner.hasNextInt()) {
                line.set(itemIndex++, lineScanner.nextInt());
            }

            maxLineSize = Math.max(maxLineSize, line.size);
        }


        // Now all input is stored in "data", I can reproduce it as follows:

        // for (int lineIndex = 0; lineIndex < data.size(); lineIndex++) {
        //     System.out.println(Arrays.toString(data.get(lineIndex).numbers));
        // }


        int[] columnSumm = new int[maxLineSize];  // Size = maxLineSize = max(line.size) <= n

        for (int lineIndex = 0; lineIndex < data.size(); lineIndex++) {

            int lineSumm = 0;

            for (int numberIndex = 0; numberIndex < data.get(lineIndex).size; numberIndex++) {

                columnSumm[numberIndex] += data.get(lineIndex).numbers[numberIndex];
                lineSumm += columnSumm[numberIndex];

                System.out.print(lineSumm);
                System.out.print(' ');
            }

            System.out.println();
        }
    }
}
