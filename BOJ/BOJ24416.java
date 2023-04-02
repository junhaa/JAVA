import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #24416 알고리즘 수업 - 피보나치 수 1
public class Main {
	
	static int callb = 0;
	static int[] dp;
	
	static int fibo(int n) {
		if(dp[n] != 0) return dp[n];
		else {
			callb ++;
			return dp[n] = (fibo(n - 1) + fibo(n - 2));
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[41];
		dp[0] = dp[1] = dp[2] = 1;
		T.fibo(n);
		System.out.println(dp[n] + " " + callb);
	}
}	
