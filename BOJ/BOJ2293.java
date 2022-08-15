import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2293 µ¿Àü 1
public class Main {
	static int[] dp, coin;
	
	static int solution(int k, int n) {
		Arrays.fill(dp, Integer.MAX_VALUE);
		Arrays.sort(coin);
		dp[0] = 0; 
		for(int i = 1 ; i * coin[0] <= k ; i ++) dp[coin[0] * i] = i;
		for(int j = 1 ; j < n ; j ++) {
			for(int i = coin[j] ; i <= k ; i ++) {
				if(dp[i - coin[j]] == Integer.MAX_VALUE) continue;
				else dp[i] = Math.min(dp[i], dp[i - coin[j]] + 1); 
			}
		}
		if(dp[k] == Integer.MAX_VALUE) return -1;
		else return dp[k];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		dp = new int[k + 1];
		coin = new int[n];
		for(int i = 0 ; i < n ; i++) coin[i] = Integer.parseInt(br.readLine());
		System.out.println(T.solution(k, n));
	}
}
