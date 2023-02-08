import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #14722 우유 도시
public class Main {

	static int[][] map;
	static long[][][] dp;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static int N;
	
	static long solution(int y, int x, int num, int cnt) {
		if(y == N - 1 && x == N - 1) { 
			return 0;
		}
		if(dp[y][x][num] != -1) return dp[y][x][num];
		long tmp = Long.MIN_VALUE;
		int nextNum = (num + 1) % 3;
		for(int i = 0 ; i < 2 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if(map[ny][nx] == nextNum) {
					tmp = Math.max(solution(ny, nx, nextNum, cnt) + 1, tmp);
				}
				else tmp = Math.max(tmp, solution(ny, nx, num, cnt));
			}
		}
		return dp[y][x][num] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N][3];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				Arrays.fill(dp[i][j], -1);
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(map[0][0] == 0) {
			System.out.println(T.solution(0, 0, 0, 0) + 1);
		}
		else System.out.println(T.solution(0, 0, 2, 0));
	}
}
