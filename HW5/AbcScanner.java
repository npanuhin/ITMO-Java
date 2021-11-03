import java.io.InputStreamReader;
import java.io.InputStream;

import java.io.IOException;


public class AbcScanner extends Scanner {

    public AbcScanner(InputStream in) throws IOException {
        super(new InputStreamReader(in, "utf-8"));
    }
    public AbcScanner(InputStream in, Delimiter delimiter) throws IOException {
        super(new InputStreamReader(in, "utf-8"), delimiter);
    }
    
    @Override
    public int nextInt() throws IOException {
        String number = nextString();

        if (number.startsWith("0x") || number.startsWith("0X")) {
            return Integer.parseUnsignedInt(number.substring(2), 16);

        } else {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < number.length(); i++) {
                if ('a' <= number.charAt(i) && number.charAt(i) <= 'j') {
                    result.append(number.charAt(i) - 'a');
                } else {
                    result.append(number.charAt(i));
                }
            }

            return Integer.parseInt(result.toString());
        }
    }
}
