import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2748 피보나치 수 2
public class Main {
	static long[] dp ;
	
	static long fibo(int n) {
		if(dp[n] != 0) return dp[n];
		if(n == 1) return dp[n] = 1;
		else if(n == 0) return dp[n] = 0;
		else return dp[n] = fibo(n - 1) + fibo(n - 2);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new long[n + 1];
		System.out.println(fibo(n));
	}
}
