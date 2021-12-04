// import java.util.Arrays;


public class Sum {
    public static void main(String[] args) {
        // System.out.println(Arrays.toString(args));

        StringBuilder builder;
        int result = 0;

        for (String arg : args) {

            builder = new StringBuilder();

            for (int j = 0; j <= arg.length(); ++j) {

                if (j < arg.length() && !Character.isWhitespace(arg.charAt(j))) {
                    builder.append(arg.charAt(j));

                } else if (builder.length() != 0) {
                    result += Integer.parseInt(builder.toString());
                    builder = new StringBuilder();
                }
            }
        }

        System.out.println(result);
    }
}
