import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #14916 °Å½º¸§µ·
public class Main {
	static int dp[];
	static int recursive(int k) {
		if(dp[k] != 0) return dp[k];
		return dp[k] = 1 + recursive(k - 2);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n == 1 || n == 3) {
			System.out.println(-1);
			return;
		}
		dp = new int[n + 1];
		
		for(int i = 1 ; i * 5 <= n ; i ++) {
			dp[i * 5] = i; 
		}
		dp[2] = 1;
		System.out.println(T.recursive(n));
	}
}
