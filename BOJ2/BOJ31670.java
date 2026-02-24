import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #31670 특별한 마법 공격
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] r = new int[n];

        for(int i = 0 ; i < n ; i ++){
            r[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(T.solve(n, r));
    }

    private long solve(int n, int[] r){
        if(n == 1) return 0;

        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = r[0];
        dp[1] = r[1];

        for(int i = 2 ; i < n ; i ++){
            dp[i] = Math.min(dp[i - 2] + r[i], dp[i - 1] + r[i]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
