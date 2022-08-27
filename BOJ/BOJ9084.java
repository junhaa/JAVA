import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #9084 µ¿Àü
public class Main {
	static int[] coin;
	static int[] dp;
	
	static int solution(int N, int K) {
		dp[0] = 1;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = coin[i] ; k <= K ; k ++) {
				dp[k] += dp[k - coin[i]];
			}
		}
		return dp[K];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			coin = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int K = Integer.parseInt(br.readLine());
			dp = new int[K + 1];
			sb.append(T.solution(N, K)).append('\n');
		}
		System.out.println(sb);
	}
}
