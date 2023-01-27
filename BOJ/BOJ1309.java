import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #1309 동물원
public class Main {

	static int[][] dp;
	static int N;
	
	
	static int solution(int i, int cnt) {
		if(dp[i][cnt] != -1) return dp[i][cnt];
		if(cnt == 0) {
			int tmp = 0;
			tmp += solution(i - 1, 0);
			tmp += solution(i - 1, 1);
			tmp += solution(i - 1, 2);
			tmp %= 9901;
			dp[i][cnt] = tmp;
		}
		else if(cnt == 1){ // 왼쪽
			int tmp = 0;
			tmp += solution(i - 1, 0);
			tmp += solution(i - 1, 2);
			tmp %= 9901;
			dp[i][cnt] = tmp;
		}
		else {
			int tmp = 0;
			tmp += solution(i - 1, 0);
			tmp += solution(i - 1, 1);
			tmp %= 9901;
			dp[i][cnt] = tmp;
		}
		return dp[i][cnt];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][3]; // 0 = 모두 빈 칸 1 = 왼쪽 2 = 오른쪽
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 1;
		dp[0][1] = 1;
		dp[0][2] = 1;	
		T.solution(N - 1, 0);
		T.solution(N - 1, 1);
		T.solution(N - 1, 2);
		System.out.println((dp[N - 1][0] + dp[N - 1][1] + dp[N - 1][2]) % 9901);
	}
}
