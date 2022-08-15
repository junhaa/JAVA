import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// BOJ #5557 1ÇÐ³â
public class Main {
	static long[][] dp;
	static int[] arr;
	
	static long solution(int N, int num) {
		dp[0][arr[0]] = 1;
		for(int i = 1 ; i < N - 1 ; i ++) {
			for(int k = 0 ; k <= 20 ; k ++) {
				if(dp[i - 1][k] != 0) {
					int tmp = k;
					if(tmp + arr[i] >= 0 && tmp + arr[i] <= 20) dp[i][tmp + arr[i]] += dp[i - 1][tmp];
					if(tmp - arr[i] >= 0 && tmp - arr[i] <= 20) dp[i][tmp - arr[i]] += dp[i - 1][tmp];
				}
			}
		}

		return dp[N - 2][num];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N - 1];
		dp = new long[N - 1][21];
		for(int i = 0 ; i < N - 1 ; i ++) arr[i] = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(N, num));
	}
}
