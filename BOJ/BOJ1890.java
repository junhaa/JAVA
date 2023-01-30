import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1890 점프
public class Main {

	static int[][] map;
	static long[][] dp;
	static int N;
	
	public static long solution(int y, int x) {
		if(y >= N || x >= N) return 0;
		if(y == N - 1 && x == N - 1) return 1;
		if(map[y][x] == 0) return 0;
		if(dp[y][x] != -1) return dp[y][x];
		long tmp = 0;
		tmp += solution(y + map[y][x], x);
		tmp += solution(y, x + map[y][x]);
		return dp[y][x] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(dp[i], -1);
		}
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution(0, 0));
	}
}
