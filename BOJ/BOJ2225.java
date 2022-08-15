import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2225 ÇÕºÐÇØ
public class Main {
	static int[] dp;
	
	static int solution(int N , int K) {
		Arrays.fill(dp, 1);
		for(int i = 1 ; i < K ; i ++) {
			for(int j = 1 ; j <= N ; j ++) {
				dp[j] = (dp[j - 1] + dp[j]) % 1000000000;
			}
		}
		return dp[N];
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1];
		System.out.println(T.solution(N, K));
	}
}
