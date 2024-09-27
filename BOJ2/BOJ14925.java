import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14925 목장 건설하기
public class Main {
	static int[][] dp;
	static boolean[][] isDisable;
	static int M, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[M][N];

		for(int i = 0 ; i < M ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++){
				int cur = Integer.parseInt(st.nextToken());
				dp[i][j] = cur == 0 ? 1 : 0;
			}
		}

		for(int i = 1 ; i < M ; i ++){
			for(int j = 1 ; j < N ; j ++){
				if(dp[i][j] == 0) continue;
				dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
			}
		}

		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < M ; i ++){
			for(int j = 0 ; j < N ; j ++){
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}
}
