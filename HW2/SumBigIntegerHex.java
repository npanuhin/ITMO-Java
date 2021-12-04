// import java.util.Arrays;
import java.math.BigInteger;

public class SumBigIntegerHex {
    public static void main(String[] args) {
        // System.out.println(Arrays.toString(args));

        BigInteger result = BigInteger.ZERO;

        for (String arg : args) {

            for (int start = 0, end = 0; end <= arg.length(); end++) {

                if (end == arg.length() || Character.isWhitespace(arg.charAt(end))) {
                    if (end > start) {
                        String curentNumber = arg.substring(start, end);

                        if (curentNumber.toLowerCase().startsWith("0x")) {
                            result = result.add(new BigInteger(curentNumber.substring(2), 16));
                        } else {
                            result = result.add(new BigInteger(curentNumber));
                        }
                    }

                    start = end + 1;
                }
            }
        }

        System.out.println(result);
    }
}
