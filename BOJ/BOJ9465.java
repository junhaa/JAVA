import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #9465 스티커
public class Main {

	static int[][] score;
	static int[][] dp;
	static int N;
	static int solution(int R, int num) { // 0 가만히 1 위 2 아래
		if(R == N) return 0;
		if(dp[R][num] != -1) return dp[R][num];
		
		int max = Integer.MIN_VALUE;
		max = Math.max(max, solution(R + 1, 0));
		if(num != 1) max = Math.max(max, solution(R + 1, 1) + score[R][0]);
		if(num != 2) max = Math.max(max, solution(R + 1, 2) + score[R][1]);
		
		return dp[R][num] = max;
	}
	
		
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			N = Integer.parseInt(br.readLine());
			score = new int[N][2];
			dp = new int[N][3];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				score[i][0] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i], -1);
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				score[i][1] = Integer.parseInt(st.nextToken());
			}
			sb.append(T.solution(0, 0)).append('\n');
		}
		System.out.println(sb);
	}
}
