import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ #17069 파이프 옮기기 2
public class Main {

	static int[][] map;
	static long[][][] dp;
	static int N;
	
	
	static long solution(int y, int x, int num) {
		if(y >= N || x >= N) return 0;
		if(dp[y][x][num] != -1) return dp[y][x][num];
		if(num == 2) { // 대각선이면
			if(map[y][x] == 1 || map[y - 1][x] == 1 || map[y][x - 1] == 1) return 0;
			if(y == N - 1 && x == N - 1) return 1;
			long tmp = 0;
			tmp += solution(y, x + 1, 0);
			tmp += solution(y + 1, x, 1);
			tmp += solution(y + 1, x + 1, 2);
			return dp[y][x][num] = tmp;
		}
		else {
			if(map[y][x] == 1) return 0;
			if(y == N - 1 && x == N - 1) return 1;
			long tmp = 0;
			if(num == 0) { // 가로
				tmp += solution(y, x + 1, 0);
				tmp += solution(y + 1, x + 1, 2);
				return dp[y][x][num] = tmp;
			}
			else { // 세로
				tmp += solution(y + 1, x, 1);
				tmp += solution(y + 1, x + 1, 2);
				return dp[y][x][num] = tmp;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N][3]; // 0 가로 1 세로 2 대각선
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(T.solution(0, 1, 0));
	}
}
