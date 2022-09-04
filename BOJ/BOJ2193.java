import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2193 ÀÌÄ£¼ö
public class Main {

	static long dp[];
	
	static long solution(int N) {
		if(N <= 2) return dp[N];
		for(int i = 3 ; i <= N ; i ++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[N];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		dp[1] = 1;
		dp[2] = 1;
		System.out.println(T.solution(N));
	}
}
