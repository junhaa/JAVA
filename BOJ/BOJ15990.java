import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #15990 1,2,3 더하기 5
public class Main {

	static int[][] dp = new int[100001][4];
	
	static int solution(int i, int num) {
		if(i < 0) return 0;
		else if(i == 0) return 1;
		if(dp[i][num] != -1) return dp[i][num];
		if(num == 1) {
			int tmp = 0;
			tmp += solution(i - 2, 2);
			tmp += solution(i - 3, 3);
			tmp %= 1000000009;
			dp[i][num] = tmp;
		}
		else if(num == 2) {
			int tmp = 0;
			tmp += solution(i - 1, 1);
			tmp += solution(i - 3, 3);
			tmp %= 1000000009;
			dp[i][num] = tmp;
		}
		else {
			int tmp = 0;
			tmp += solution(i - 1, 1);
			tmp += solution(i - 2, 2);
			tmp %= 1000000009;
			dp[i][num] = tmp;
		}
		return dp[i][num];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < 100001 ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			long tmp = 0;
			tmp += T.solution(N - 1, 1);
			tmp += T.solution(N - 2, 2);
			tmp += T.solution(N - 3, 3);
			tmp %= 1000000009;
			sb.append(tmp + "\n");
		}
		System.out.println(sb);
	}

}
