import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16395 ÆÄ½ºÄ®ÀÇ »ï°¢Çü
public class Main {
	static int dp[][];
	
	static int combi(int n, int k) {
		if(dp[n][k] != 0) return dp[n][k];
		if(n == k || n == 1) return dp[n][k] = 1;
		else if(k == 0) return dp[n][k] = 1;
		return dp[n][k] = combi(n - 1, k - 1) + combi(n - 1, k);
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		dp = new int[n + 1][n + 1];
		
		if(n == 0 && k == 0) System.out.println(1);
		else System.out.println(T.combi(n - 1, k - 1));
	}
}
