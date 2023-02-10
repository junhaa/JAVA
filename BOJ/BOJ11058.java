import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11058 크리보드
public class Main {

	static long[] dp = new long[101];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i <= N ; i ++) {
			dp[i] = Math.max(dp[i], i);
			for(int j = 3 ; j <= 100 - i ; j ++) {
				dp[i + j] = Math.max(dp[i + j], dp[i] * (j - 1));
			}
		}
		System.out.println(dp[N]);
	}
}
