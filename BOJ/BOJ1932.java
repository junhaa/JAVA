import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1932 Á¤¼ö »ï°¢Çü
public class Main {

	static int[][] ta;
	static int[][] dp;
	
	static int solution(int n) {
		int answer = dp[0][0];
		for(int i = 0 ; i < n - 1 ; i ++) {
			for(int k = 0 ; k <= i ; k ++) {
				dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][k] + ta[i + 1][k]);
				dp[i + 1][k + 1] = Math.max(dp[i + 1][k + 1], dp[i][k] + ta[i + 1][k + 1]);
				answer = Math.max(answer, dp[i + 1][k]);
				answer = Math.max(answer, dp[i + 1][k + 1]);
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ta = new int[n][n];
		dp = new int[n][n];
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k <= i ; k ++) {
				ta[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = ta[0][0];
		if(n == 1) System.out.println(ta[0][0]);
		else System.out.println(T.solution(n));
	}
}
