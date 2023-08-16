import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2240 자두나무
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] dp = new int[T + 1][W + 1];
        int[] arr = new int[T + 1];
        for(int i = 1 ; i <= T ; i ++){
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }
        for(int i = 1 ; i <= T ; i ++) {
            dp[i][0] = dp[i - 1][0];
            if(arr[i] == 0) dp[i][0] += 1;
        }
        for(int i = 1 ; i <= T ; i ++){
            for(int j = 1 ; j <= Math.min(W, i) ; j ++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                dp[i][j] += (j % 2 == arr[i] ? 1 : 0);
            }
        }
        int answer = 0;
        for(int i = 0 ; i <= W ; i ++){
            answer = Math.max(dp[T][i], answer);
        }
        System.out.println(answer);
    }
}
