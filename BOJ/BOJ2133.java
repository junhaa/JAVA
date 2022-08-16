import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2133 타일 채우기
public class Main {
	static int[] dp;
	static int solution(int N) {
		if(dp[N] != 0) return dp[N];
		int tmp = 0;
		if(N >= 4) {
			for(int i = 4 ; i <= N ; i += 2) {
				tmp += 2 * solution(N - i);
			}
		}
		tmp += 3 * solution(N - 2);
		return dp[N] = tmp;
	}

	 public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N % 2 == 1) {
			System.out.println("0");
			return;
		}
		dp = new int[N + 1];
		if(N >= 2) dp[2] = 3;
		dp[0] = 1;
		System.out.println(T.solution(N));
	 }
}
