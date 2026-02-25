import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #20528 끝말잇기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        char p = st.nextToken().charAt(0);

        for (int i = 1; i < N; i++) {
            if(st.nextToken().charAt(0) != p) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
