import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #15988 1,2,3 ¥ı«œ±‚ 3
public class Main {
	static long[] dp = new long[1000001];
	
	static long solution(int num) {
		if(dp[num] != 0) return dp[num];
		for(int i = 4 ; i <= num ; i ++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
		}
		return dp[num];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		while(test --> 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(T.solution(n)).append('\n');
		}
		
		System.out.println(sb);
	}

}
