import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #11048 이동하기
public class Main {

	static int[][] map, dp;
	
	
	static int solution(int y, int x) {
		if(y < 0 || x < 0) return 0;
		if(dp[y][x] != -1) return dp[y][x];
		dp[y][x] = Math.max(solution(y - 1, x) + map[y][x], solution(y - 1, x - 1) + map[y][x]);
		dp[y][x] = Math.max(dp[y][x], solution(y, x - 1) + map[y][x]);
		return dp[y][x];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution(N - 1, M - 1));
	}
}
