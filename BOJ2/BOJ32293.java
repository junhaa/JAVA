import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #32293 ABB to BA (Hard)
public class Main {
    private static final String PREV_STRING = "ABB";
    private static final String CONVERTED_STRING = "BA";

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            sb.append(main.convert(input)).append("\n");
        }

        System.out.println(sb);
    }

    private String convert(String input) {
        StringBuilder buf = new StringBuilder();
        for (char s : input.toCharArray()) {
            buf.append(s);
            if (buf.length() >= PREV_STRING.length()) {
                boolean canChange = true;
                for (int j = 0; j < PREV_STRING.length(); j++) {
                    if (buf.charAt(buf.length() - PREV_STRING.length() + j) != PREV_STRING.charAt(j)) {
                        canChange = false;
                        break;
                    }
                }
                if (canChange) {
                    buf.setLength(buf.length() - PREV_STRING.length());
                    buf = convertRemain(buf);
                }
            }
        }

        return buf.toString();
    }

    private StringBuilder convertRemain(StringBuilder buf){
        String cat = CONVERTED_STRING;
        int length = buf.length();
        for(int i = length - 2; i >= 0; i -= 2) {
            if(cat.charAt(0) != 'B') {
                break;
            }
            boolean canChange = true;
            for(int j = 0 ; j < 2; j++) {
                if(buf.charAt(i + j) != PREV_STRING.charAt(j)) {
                    canChange = false;
                    break;
                }
            }
            if(!canChange) {
                break;
            }

            buf.setLength(buf.length() - 2);
            cat = CONVERTED_STRING + cat.substring(1);
        }
        return buf.append(cat);
    }
}
