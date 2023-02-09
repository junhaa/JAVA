import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #3933 라그랑주의 네 제곱수 정리
public class Main {

	static int[][][] dp = new int[(int)Math.pow(2, 15) + 1][182][5];
	
	static int solution(int cur, int last, int num) {
		if(num == 4 && cur > 0) return 0;
		if(cur == 0) return 1;
		if(dp[cur][last][num] != -1) return dp[cur][last][num];
		int sum = 0;
		for(int i = last ; i >= 1 ; i --) {
			if(cur - (i * i) >= 0) {
				sum += solution(cur - (i * i), i, num + 1);
			}
		}
		return dp[cur][last][num] = sum;
	}
 	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < (int)Math.pow(2, 15); i ++) {
			for(int j = 0 ; j < 182 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		while(true) {
			int cur = Integer.parseInt(br.readLine());
			if(cur == 0) break;
			int sum = 0;
			for(int i = 1 ; i <= (int)Math.sqrt(cur) ; i ++) {
				sum += T.solution(cur - i * i, i, 1);
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);
	}
}
