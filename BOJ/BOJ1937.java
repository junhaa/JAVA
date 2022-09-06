import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1937 øÂΩ…¿Ô¿Ã ∆«¥Ÿ
public class Main {

	static int[][] bamboo, dp;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int n, answer = Integer.MIN_VALUE;
	
	static int DFS(int y, int x) {
		dp[y][x] = 0;
		int max = 1;
		for(int i = 0 ; i < 4 ; i ++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(yy >= 0 && xx >= 0 && yy < n && xx < n && bamboo[yy][xx] > bamboo[y][x]) {
				if(dp[yy][xx] != -1) max = Math.max(max, dp[yy][xx] + 1);
				else max = Math.max(max, DFS(yy, xx) + 1);
			}
		}
		return dp[y][x] = max;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		bamboo = new int[n][n];
		dp = new int[n][n];
		for(int i = 0 ; i < n ; i ++) {
			Arrays.fill(dp[i], -1);
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < n ; k ++) {
				bamboo[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < n ; i ++) {
			for(int k = 0 ; k < n ; k ++) {
				if(dp[i][k] == -1) { 
					answer = Math.max(answer, DFS(i, k));
				}
			}
		}
		System.out.println(answer);
	}
}
