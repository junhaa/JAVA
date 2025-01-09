import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #23083 꿀벌 승연이
public class Main {
    private final static int MOD = 1_000_000_007;
    static int[][] dx = {{ -1, -1, 0 }, { -1, -1, 0 }}, dy = {{ 1, 0, -1 }, { 0, -1, -1}}; // 짝, 홀

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] isBlank = new boolean[N][M];

        int K = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < K ; i ++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            isBlank[y][x] = true;
        }

        System.out.println(solve(N, M, isBlank));
    }

    private static int solve(int N, int M, boolean[][] isBlank){
        int[][] dp = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            if(!isBlank[i][0]) dp[i][0] = 1;
            else break;
        }

        for(int i = 1 ; i < M ; i ++){
            for(int j = 0 ; j < N ; j ++){
                if(isBlank[j][i]) continue;
                for(int k = 0 ; k < 3 ; k ++){
                    int nx = i + dx[(i + 1) & 1][k];
                    int ny = j + dy[(i + 1) & 1][k];
                    if(!checkBound(N, M, ny, nx) || isBlank[ny][nx]) continue;
                    dp[j][i] += dp[ny][nx];
                    dp[j][i] %= MOD;
                }
            }
        }

        return dp[N - 1][M - 1];
    }

    private static boolean checkBound(int N, int M, int y, int x){
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
