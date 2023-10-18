import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #25306 연속 XOR
public class Main {

    static long getXOR(long num) {
        long rem = num % 4;
        if (rem == 0) {
            return num;
        } else if (rem == 1) {
            return 1;
        } else if (rem == 2) {
            return 1 ^ num;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        System.out.println(getXOR(A - 1) ^ getXOR(B));
    }
}
