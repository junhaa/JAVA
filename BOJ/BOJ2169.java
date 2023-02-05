import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2169 로봇 조종하기 
public class Main {

	static int N, M;
	static int[][] map;
	static int[][][] dp; // r l
	
	public void solution() {
		dp[0][0][0] = map[0][0];
		for(int i = 1 ; i < M ; i ++) {
			dp[0][i][0] = dp[0][i - 1][0] + map[0][i];
		}
		
		for(int i = 1 ; i < N ; i ++) {
		dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][0][1]) + map[i][0];
		dp[i][M - 1][1] = Math.max(dp[i - 1][M - 1][0], dp[i - 1][M - 1][0]) + map[i][M - 1]; 
			for(int j = 1 ; j < M ; j ++) {
				dp[i][j][0] = Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i][j - 1][0]) + map[i][j];
			}
			for(int j = M - 2 ; j >= 0 ; j --) {
				dp[i][j][1] = Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i][j + 1][1]) + map[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M][2];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], Integer.MIN_VALUE);
			}
		}
		T.solution();
		System.out.println(Math.max(dp[N - 1][M - 1][0], dp[N - 1][M - 1][1]));
	}
}
