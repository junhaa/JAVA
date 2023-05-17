import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17271 리그 오브 레전설 (Small)
public class Main {
	
	static int dp[];
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1];
		dp[1] = 1;
		if(M <= N) {
			dp[M] += 1;
		}
		for(int i = 2 ; i <= N ; i ++) {
			if(i - M >= 1) {
				dp[i] += dp[i - M];
			}
			dp[i] += dp[i - 1];
			dp[i] %= MOD;
		}
		System.out.println(dp[N]);
	}
}
