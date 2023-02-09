import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #16194 카드 구매하기 2
public class Main {

	static int[] cost;
	static int dp[][];
	static int dp2[];
	
	static int solution(int num, int last, int sum) {
		if(num == 0) return sum;
		int min = Integer.MAX_VALUE;
		for(int i = last ; i >= 1 ; i --) {
			if(num - i >= 0)
			min = Math.min(min, solution(num - i, i, sum + cost[i]));
		}
		return dp[num][last] = min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cost = new int[N + 1];
		dp = new int[N + 1][N + 1];
		dp2 = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		int min = Integer.MAX_VALUE;
		Arrays.fill(dp2, 100000000);
		dp2[0] = 0;
		//for(int i = N ; i >= 1 ; i --) {
		//	min = Math.min(min, T.solution(N - i, i, cost[i]));
		//}
		for(int i = 1 ; i <= N ; i ++) {
			for(int j = 1 ; j <= i ; j ++) {
				dp2[i] = Math.min(dp2[i], cost[j] + dp2[i - j]);
			}
		}
		//System.out.println(min);
		System.out.println(dp2[N]);
	}
}
