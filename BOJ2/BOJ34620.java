import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #34620 군꺾문자열
public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(main.solve(a, b));
    }

    private String solve(int a, int b) {
        StringBuilder sb = new StringBuilder();

        if((b & (b - 1)) != 0) return "-1";

        sb.append("G");
        while(b > 0) {
            b >>= 1;
            if(b != 0) sb.append("K");

            a >>= 1;
            if((a & 1) == 1) sb.append("G");
        }

        if(a != 0) {
            sb.append("K".repeat((int)Math.pow(2, a)));
        }

        return sb.toString();
    }
}
