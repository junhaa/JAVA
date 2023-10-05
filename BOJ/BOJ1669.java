import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1669 멍멍이 쓰다듬기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int dif = Y - X;
        if (dif == 0) {
            System.out.println(0);
            return;
        }
        long i = 0;
        for (; i <= 46341; i++) {
            long cur = i * i;
            if (cur == dif) {
                System.out.println(i * 2 - 1);
                return;
            } else if (cur > dif) {
                if (dif > i * (i - 1)) {
                    System.out.println(i * 2 - 1);
                    return;
                } else {
                    System.out.println(i * 2 - 2);
                    return;
                }

            }
        }
        System.out.println(-1);
    }
}
