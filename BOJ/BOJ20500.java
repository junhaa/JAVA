import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #20500 Ezreal 여눈부터 가네 ㅈㅈ
public class Main {

	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) System.out.println("0");
		dp = new int[N + 1][3];
		dp[2][0] = 1;
		dp[2][1] = 1;
		for(int i = 3 ; i <= N ; i ++) {
			dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 1000000007;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 1000000007;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000007;
 		}
		System.out.println(dp[N][0]);
	}
}
