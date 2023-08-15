import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #1757 달려달려
public class Main {

    static int[][] dp;
    static int[] dis;
    static int N, M;
    static boolean OOB(int x){
        if(x >= 0 && x <= M) return false;
        else return true;
    }
    static int getDis(int i, int j){
        int lt = -1;
        int rt = -1;
        if(j == 0) lt = 0;
        else if(j == M) rt = 0;
        if(lt == -1) lt = dp[i - 1][j - 1] + dis[i];
        if(rt == -1) rt = dp[i - 1][j + 1];
        if(j == 0){
            lt = Math.max(dp[i - 1][j], lt);
        }
        return Math.max(lt, rt);
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        dis = new int[N + 1];
        for(int i = 1 ; i <= N ; i ++){
            dis[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1 ; i <= N ; i ++){
            dp[i][0] = dp[i - 1][0];
            for(int j = 1 ; j <= Math.min(i, M) ; j ++){
                dp[i][j] = dp[i - 1][j - 1] + dis[i];
            }
            for(int j = 1 ; j <= M ; j ++){
                if(j > i) break;
                dp[i][0] = Math.max(dp[i][0], dp[i - j][0 + j]);
            }
        }
        System.out.println(dp[N][0]);
    }
}
