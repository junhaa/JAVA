import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #11057 오르막 수
public class Main {

	static int[][] dp;
	static int N;
	
	public static int solution(int i, int num) {
		if(i == N - 1) return 1; 
		if(dp[i][num] != -1) return dp[i][num];
		int tmp = 0;
		for(int j = num ; j < 10 ; j ++) {
			tmp += solution(i + 1, j);
			tmp %= 10007;
		}
		return dp[i][num] = tmp;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][10];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		int answer = 0;
		for(int i = 0 ; i < 10 ; i ++) {
			answer += T.solution(0, i);
			answer %= 10007;
		}
		System.out.println(answer);
	}
}
