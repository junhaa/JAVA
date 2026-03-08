import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #28325 호숫가의 개미굴
public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] c = new long[N];

        for(int i = 0; i < N; i++) {
            c[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(main.solve(N, c));
    }

    private long solve(int N, long[] c) {
        long sum = 0;
        for(int i = 0 ; i < N ; i ++){
            sum += c[i];
        }

        long[][][] dp = new long[2][2][N]; // 0인덱스 선택 여부, 해당 위치 선택했는지

        if(c[0] == 0){
            dp[1][1][0] = 1;
        }

        for(int i = 1 ; i < N ; i ++) {
            dp[0][0][i] = Math.max(dp[0][1][i - 1], dp[0][0][i - 1]);
            dp[1][0][i] = Math.max(dp[1][1][i - 1], dp[1][0][i - 1]);

            if(c[i] == 0) {
                dp[0][1][i] = dp[0][0][i - 1] + 1;
                dp[1][1][i] = dp[1][0][i - 1] + 1;
            }
        }

        dp[1][1][N - 1] --;

        long max = Math.max(dp[0][0][N - 1], dp[0][1][N - 1]);
        max = Math.max(max, dp[1][0][N - 1]);
        max = Math.max(max, dp[1][1][N - 1]);

        return max + sum;
    }
}
