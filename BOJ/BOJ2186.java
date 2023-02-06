import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2186 문자판
public class Main {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[][][] dp;
	static char[][] map;
	static String str;
	static int N, M;
	static boolean visited[][];
	
	static int solution(int y, int x, int idx, int k) {
		if(dp[y][x][idx] != -1) return dp[y][x][idx];
		if(idx == str.length() - 1) return dp[y][x][idx] = 1;
		int tmp = 0;
		char next = str.charAt(idx + 1);
		for(int i = 0 ; i < 4 ; i ++) {
			for(int j = 1 ; j <= k ; j ++) {
				int nx = x + dx[i] * j;
				int ny = y + dy[i] * j;
				if(nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] == next) {
					tmp += solution(ny, nx, idx + 1, k);
				}
			}
		}
		return dp[y][x][idx] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j);
			}
		}
		str = br.readLine();
		dp = new int[N][M][str.length()];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map[i][j] == str.charAt(0)) {
					answer += T.solution(i, j, 0, K);
				}
			}
		}
		System.out.println(answer);
	}
}
