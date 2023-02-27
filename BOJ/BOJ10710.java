import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #10710 실크로드
public class Main {

	static int[][] dp;
	static int[] d, c;
	static int N, M;
	
	static int solution(int road, int day) {
		if(road == N + 1) return 0;
		if(dp[road][day] != -1) return dp[road][day];
		
		int min = Integer.MAX_VALUE;
		if(N - road < M - day) {
			min = Math.min(min, solution(road, day + 1));
		}
		min = Math.min(min, solution(road + 1, day + 1) + d[road] * c[day]);
		
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][M + 1];
		d = new int[N + 1];
		c = new int[M + 1];
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(dp[i], (int)1e9);
		}
		Arrays.fill(dp[0], 0);
		for(int i = 1 ; i <= N ; i ++) {
			d[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1 ; i <= M ; i ++) {
			c[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1 ; i <= M ; i ++) { // day
			for(int j = 1 ; j <= N ; j ++) { // road
				dp[j][i] = Math.min(dp[j][i - 1], dp[j - 1][i - 1] + d[j] * c[i]);
			}
		}
		//System.out.println(T.solution(0, 0));
		System.out.println(dp[N][M]);
	}
}
