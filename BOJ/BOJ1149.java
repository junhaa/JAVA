import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1149 RGB거리
public class Main {

	static int[][] dp, cost;
	static int N;
	
	static int RGB(int num, int last) {
		if(dp[num][last] != -1) return dp[num][last];
		if(num == N) return 0;
		int tmp = Integer.MAX_VALUE;
		for(int i = 0 ; i < 3 ; i ++) {
			if(last != i) { 
				tmp = Math.min(tmp, RGB(num + 1, i) + cost[num][i]);
			}
		}
		return dp[num][last] = tmp; 
		
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][4];
		cost = new int[N][3];
		StringTokenizer st;
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j ++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.RGB(0, 3));
	}
}
