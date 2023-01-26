import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #2156 포도주 시식
public class Main {

	static int[] q;
	static int[][] dp;
	
	static int solution(int idx,  int cnt) {
		if(idx < 0) return 0;
		if(dp[idx][cnt] != -1) return dp[idx][cnt];
		if(cnt == 2) {
			dp[idx][cnt] = Math.max(solution(idx - 2, 1) + q[idx], solution(idx - 3, 1) + q[idx]);
			
		}
		else {
			dp[idx][cnt] = Math.max(solution(idx - 2, 1) + q[idx], solution(idx - 1, cnt + 1) + q[idx]);
			dp[idx][cnt] = Math.max(dp[idx][cnt], solution(idx - 3, 1) + q[idx]);
		}
		return dp[idx][cnt];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		q = new int[n];
		dp = new int[n][3];
		for(int i = 0 ; i < n ; i ++) Arrays.fill(dp[i], -1);
		for(int i = 0 ; i < n ; i ++) {
			q[i] = Integer.parseInt(br.readLine());
		}
		for(int i = n - 1 ; i >= 0 ; i --) T.solution(i, 1);
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 1 ; j < 3 ; j ++) {
				max = Math.max(dp[i][j], max);
			}
		}
		System.out.println(max);
	}
}
