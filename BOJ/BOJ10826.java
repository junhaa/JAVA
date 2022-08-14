import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// BOJ #10826 피보나치 수 4
public class Main {
	static BigInteger[] dp;
	
	static BigInteger fibo(int n) {
		dp[0] = new BigInteger("0");
		if(n > 0) dp[1] = new BigInteger("1");
		for(int i = 2 ; i <= n ; i ++)  dp[i] = dp[i-1].add(dp[i-2]);
		return dp[n];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new BigInteger[n + 1];
		System.out.println(T.fibo(n));
	}
}
