import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Reverse {
    public static class Numbers {

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
        List<Numbers> data = new ArrayList<Numbers>();

        int maxLineSize = 0;

        while (fileScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());

            Numbers line = new Numbers();
            data.add(line);

            int itemIndex = 0;
            while (lineScanner.hasNextInt()) {
                line.set(itemIndex++, lineScanner.nextInt());
            }

            maxLineSize = Math.max(maxLineSize, line.size);
        }

        // for (int i = 0; i < data.size(); i++) {
        //     System.out.println(Arrays.toString(data.get(i).numbers));
        // }

        for (int lineIndex = data.size() - 1; lineIndex >= 0; lineIndex--) {

            for (int numberIndex = data.get(lineIndex).size - 1; numberIndex >= 0; numberIndex--) {
                System.out.print(data.get(lineIndex).numbers[numberIndex]);
                System.out.print(' ');
            }

            System.out.println();
        }
    }
}
