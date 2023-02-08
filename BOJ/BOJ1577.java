import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1577 도로의 개수

public class Main {
	
	static long[][] dp;
	static int[][] block;
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };
	static int N, M;
	
	static long solution(int y, int x) {
		if(y == N && x == M) return 1;
		if(dp[y][x] != -1) return dp[y][x];
		long tmp = 0;
		for(int i = 0 ; i < 2 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx > M || ny < 0 || ny > N) continue;
			if(i == 0 && (block[y][x] == 1 || block[y][x] == 3)) {
				continue;
			}
			if(i == 1 && (block[y][x] == 2 || block[y][x] == 3)) {
				continue;
			}
			tmp += solution(ny, nx);
		}
		return dp[y][x] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		dp = new long[N + 1][M + 1];
		block = new int[N + 1][M + 1];
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if(x2 < x1) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if(y2 < y1) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			if(y1 < y2) {
				if(block[y1][x1] == 3 || block[y1][x1] == 2);
				else {
					block[y1][x1] += 2;
				}
			}
			if(x1 < x2) {
				if(block[y1][x1] == 1 || block[y1][x1] == 3);
				else {
					block[y1][x1] += 1;
				}
			}
		}
		System.out.println(T.solution(0, 0));
	}
}