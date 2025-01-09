import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #3976 역습
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] dp = new int[2][N];
            int[] shoot = new int[2]; // 마지막 슈팅 난이도

            for(int i = 0 ; i < 2 ; i ++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = Integer.parseInt(st.nextToken());
            dp[1][0] = Integer.parseInt(st.nextToken());

            shoot[0] = Integer.parseInt(st.nextToken());
            shoot[1] = Integer.parseInt(st.nextToken());

            int[][][] pass = new int[2][2][N - 1]; // 스트라이커, [패스, 드리블]

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N - 1 ; i ++){
                pass[0][0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N - 1 ; i ++){
                pass[0][1][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N - 1; i ++){
                pass[1][0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N - 1; i ++){
                pass[1][1][i] = Integer.parseInt(st.nextToken());
            }
            sb.append(getMinCost(dp, shoot, pass, N)).append("\n");
        }
        System.out.println(sb);
    }

    private static int getMinCost(int[][] dp, int[] shoot, int[][][] pass, int N){
        for(int i = 1 ; i < N ; i ++){
            dp[0][i] = Math.min(dp[0][i - 1] + pass[0][1][i - 1], dp[1][i - 1] + pass[1][0][i - 1]);
            dp[1][i] = Math.min(dp[1][i - 1] + pass[1][1][i - 1], dp[0][i - 1] + pass[0][0][i - 1]);
        }

        return Math.min(dp[0][N - 1] + shoot[0], dp[1][N - 1] + shoot[1]);
    }
}
