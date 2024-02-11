import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #3955 캔디 분배
public class Main {

    static int[] EEA(int a, int b) {
        int r1 = a, r2 = b, s1 = 1, s2 = 0, t1 = 0, t2 = 1;

        while (r2 != 0) {
            int q = r1 / r2;
            int r = r1 % r2;
            int s = s1 - q * s2;
            int t = t1 - q * t2;
            r1 = r2;
            r2 = r;
            s1 = s2;
            s2 = s;
            t1 = t2;
            t2 = t;
        }

        int gcd = Math.abs(r1); // Ensure positive GCD
        if (gcd != 0) { // Avoid division by zero
            if (a * s1 + b * t1 < 0) { // Check if adjustment is needed
                s1 = -s1;
                t1 = -t1;
            }
        }
        return new int[] {gcd, s1, t1};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[] ret = EEA(C, K);
            while (ret[1] <= 0 || ret[2] >= 0) {
                long addSum = (long)C * K;
                ret[1] += K;
                ret[2] += addSum / K * -1;
            }
            if(ret[1] > 1_000_000_000) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }
            if (1 % ret[0] == 0) {
                long mul = 1 / ret[0];
                    sb.append(ret[1] * mul + "\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.println(sb);
    }
}
