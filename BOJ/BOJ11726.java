import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11726 2xn ≈∏¿œ∏µ
public class Main {
	static int[] dp;
	
	static int solution(int n) {
		if(dp[n] != 0) return dp[n];
		if(n == 2) return dp[n] = 2;
		else if(n == 1) return dp[n] = 1;
		else return dp[n] = (solution(n - 1) + solution(n - 2)) % 10007;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		System.out.println(T.solution(n));
	}	
}
