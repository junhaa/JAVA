import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17404 RGB거리 2
public class Main {

	static int[][][] dp; // idx last first
	static int[][] cost;
	static int N;
	
	public static int solution(int idx, int last, int first) {
		if(last != -1 && dp[idx][last][first] != -1) return dp[idx][last][first];
		if(idx == N - 1) { // base case
			int tmp = Integer.MAX_VALUE;
			for(int i = 0 ; i < 3 ; i ++) {
				if(i == last || i == first) continue;
				tmp = Math.min(cost[idx][i], tmp);
			}
			return dp[idx][last][first] = tmp;
		}
		
		int tmp = Integer.MAX_VALUE;
		if(idx == 0) {
			int tmpp = solution(idx + 1, first, first) + cost[idx][first];
			for(int i = 0 ; i < 3 ; i ++) {
				dp[idx][i][first] = tmpp;
			} 
			return dp[idx][0][first];
		}
		for(int i = 0 ; i < 3 ; i ++) {
			if(i == last) continue;
			tmp = Math.min(solution(idx + 1, i, first) + cost[idx][i], tmp);
		}
		
		return dp[idx][last][first] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dp = new int[N][3][3];
		cost = new int[N][3];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j ++) {
				Arrays.fill(dp[i][j], -1);
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int min = Integer.MAX_VALUE;
		min = Math.min(solution(0, -1, 0), min);
		min = Math.min(solution(0, -1, 1), min);
		min = Math.min(solution(0, -1, 2), min);
		System.out.println(min);
	}
}
