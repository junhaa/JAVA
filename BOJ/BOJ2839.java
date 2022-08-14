import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2839 ¼³ÅÁ ¹è´Þ
public class Main {
	static int[] dp;
	
	static int getMin(int min, int x) {
		if(x == -1) return min;
		else if(min > x) return x;
		else return min;
	}
	
	static int solution(int n) {
		if(dp[n] != 0) return dp[n];
		for(int i = 6 ; i <= n ; i ++ ) {
			int min = Integer.MAX_VALUE;
			min = getMin(min, dp[i - 3]);
			min = getMin(min, dp[i - 5]);
			if(min == Integer.MAX_VALUE) dp[i] = -1;
			else dp[i] = min + 1;
		}
		return dp[n];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		dp[1] = -1;
		dp[2] = -1;
		dp[3] = 1;
		if(N > 3) dp[4] = -1;
		if(N > 4) dp[5] = 1;
		System.out.println(T.solution(N));
	}
}
