import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #2688 줄어들지 않아
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] dp = new long[66][10];
		Arrays.fill(dp[1], 1);
		long sum = 10;
		for(int i = 2 ; i <= 65 ; i ++) {
			for(int k = 0 ; k < 10 ; k ++) {
				if(k == 0) {
					dp[i][k] = sum;
				}
				else {
					dp[i][k] = dp[i][k - 1] - dp[i - 1][k - 1];
					sum += dp[i][k];
				}
			}
		}
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < test ; i ++) {
			sb.append(dp[Integer.parseInt(br.readLine()) + 1][0] + "\n");
		}
		System.out.println(sb);
	}
}	
