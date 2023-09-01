import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #4446 ROT13
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder sb = new StringBuilder();
        char[] al = { 'b', 'k', 'x', 'z', 'n', 'h', 'd', 'c', 'w', 'g', 'p', 'v', 'j', 'q', 't', 's', 'r', 'l', 'm', 'f'};
        while((input = br.readLine()) != null) {
            String answer = "";
            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);
                boolean isupper = false;
                if (Character.isUpperCase(cur)) {
                    cur = Character.toLowerCase(cur);
                    isupper = true;
                }
                char tmp = ' ';
                if (!Character.isAlphabetic(cur)) {
                    answer += cur;
                    continue;
                } else if (cur == 'a') {
                    tmp = 'e';
                } else if (cur == 'i') {
                    tmp = 'o';
                } else if (cur == 'y') {
                    tmp = 'u';
                } else if (cur == 'e') {
                    tmp = 'a';
                } else if (cur == 'o') {
                    tmp = 'i';
                } else if (cur == 'u') {
                    tmp = 'y';
                } else {
                    for (int j = 0; j < al.length; j++) {
                        if (al[j] == cur) {
                            tmp = al[(j + 10) % al.length];
                            break;
                        }
                    }
                }
                if (isupper) {
                    tmp = Character.toUpperCase(tmp);
                }
                answer += tmp;
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
}
