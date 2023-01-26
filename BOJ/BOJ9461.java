import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9461 파도반 수열
public class Main {

	static long[] dp = new long[101];
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 1;
	
		for(int i = 3 ; i <= 100 ; i ++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}
		while(test -- > 0) {
			sb.append(dp[Integer.parseInt(br.readLine()) - 1] + "\n");
		}
		System.out.println(sb);
	}
}
