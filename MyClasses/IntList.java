import java.util.Arrays;


public class IntList {
    public int[] numbers = new int[0];
    public int size = 0;

    public void set(int pos, int value) {
        while (pos >= this.numbers.length) {
            this.numbers = Arrays.copyOf(this.numbers, Math.max(this.numbers.length * 2, 1));
        }
        this.numbers[pos] = value;
        this.size = Math.max(this.size, pos + 1);
    }

    public void add(int value) {
        set(this.size, value);
    }

    public int get(int pos) {
        return this.numbers[pos];
    }
}
