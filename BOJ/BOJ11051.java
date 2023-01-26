import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11051 이항 계수 2
public class Main {
	
	static int[][] dp = new int[1001][1001];
	
	public static int combi(int n, int r) {
		if(n < r) return 0;
		if(dp[n][r] != 0) return dp[n][r];
		if(n == 1 || r == 0) return dp[n][r] = 1;
		return dp[n][r] = (combi(n - 1, r) + combi(n - 1, r - 1)) % 10007;
	}

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(combi(N, K));
	}
}
