import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27396 문자열 변환과 쿼리
public class Main {

    private static int ALPHABET_SIZE = 26;

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        int n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        char[] convertMap = new char[ALPHABET_SIZE * 2];

        for(int i = 0 ; i < ALPHABET_SIZE ; i ++){
            convertMap[i] = (char)('a' + i);
            convertMap[ALPHABET_SIZE + i] = (char)('A' + i);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1 -> main.change(st.nextToken().charAt(0), st.nextToken().charAt(0), convertMap);
                case 2 -> sb.append(main.convert(S, convertMap)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private void change(char from, char to, char[] convertMap) {
        for(int i = 0 ; i < ALPHABET_SIZE * 2 ; i ++) {
            if(convertMap[i] == from) {
                convertMap[i] = to;
            }
        }
    }

    private String convert(String S, char[] convertMap) {
        StringBuilder sb = new StringBuilder();
        for(char cur: S.toCharArray()) {
            sb.append(convertMap[convertCharToIdx(cur)]);
        }

        return sb.toString();
    }

    private int convertCharToIdx(char c) {
        if(Character.isUpperCase(c)) {
            return c - 'A' + ALPHABET_SIZE;
        }
        return c - 'a';
    }
}
