import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #14863 서울에서 경산까지 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][K + 1];
        st = new StringTokenizer(br.readLine());
        int wt0 = Integer.parseInt(st.nextToken());
        int wc0 = Integer.parseInt(st.nextToken());
        int bt0 = Integer.parseInt(st.nextToken());
        int bc0 = Integer.parseInt(st.nextToken());
        dp[0][wt0] = wc0;
        dp[0][bt0] = Math.max(dp[0][bt0], bc0);

        for(int i = 1 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int wt = Integer.parseInt(st.nextToken());
            int wc = Integer.parseInt(st.nextToken());
            int bt = Integer.parseInt(st.nextToken());
            int bc = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j <= K ; j ++){
                if(dp[i - 1][j] == 0) continue;
                int nwt = j + wt;
                if(nwt <= K){
                    dp[i][nwt] = Math.max(dp[i - 1][j] + wc, dp[i][nwt]);
                }
                int nbt = j + bt;
                if(nbt <= K){
                    dp[i][nbt] = Math.max(dp[i - 1][j] + bc, dp[i][nbt]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i <= K ; i ++){
            max = Math.max(max, dp[N - 1][i]);
        }
        System.out.println(max);
    }
}
