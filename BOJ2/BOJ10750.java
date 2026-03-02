import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// BOJ #10750 Censoring
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        char[] tChar = T.toCharArray();
        StringBuilder buf = new StringBuilder();

        for (char s : S.toCharArray()) {
            buf.append(s);
            while (buf.length() >= T.length()) {
                boolean flag = true;
                for (int j = 0; j < T.length(); j++) {
                    if (buf.charAt(buf.length() - T.length() + j) != tChar[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    buf.setLength(buf.length() - T.length());
                    continue;
                }
                break;
            }
        }
        System.out.println(buf);
    }
}
