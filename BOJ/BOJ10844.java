import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #10844 쉬운 계단 수
public class Main {

	static int[][] dp; 
	
	public int solution(int i, int num) {
		if(i == 1) return 1;
		if(dp[i][num] != -1) return dp[i][num];
		if(num == 9) {
			dp[i][num] = solution(i - 1, 8);
		}
		else if(num == 0) {
			dp[i][num] = solution(i - 1, 1);
		}
		else {
			dp[i][num] = (solution(i - 1, num + 1) + solution(i - 1, num - 1)) % 1000000000;
		}
		return dp[i][num];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		dp = new int[N + 1][10];
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i = 9 ; i >= 1 ; i --) {
			answer += T.solution(N, i);
			answer %= 1000000000;
		}
		System.out.println(answer);
	}
}
