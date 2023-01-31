import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #2410 2의 멱수의 합
public class Main {

	static int dp[][];
	
	static int solution(int num, int last) {
		if(num < 0) return 0;
		if(dp[num][last] != -1) return dp[num][last];
		if(num == 0) return dp[num][last] = 1;
		int tmp = 0;
		for(int i = last ; i >= 0 ; i --) {
			tmp += solution(num - (int)Math.pow(2, i), i);
			tmp %= 1000000000;
		}
		return dp[num][last] = tmp;
	}
 	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][21];
		for(int i = 0 ; i <= N ; i ++) Arrays.fill(dp[i], -1);
		int m = 0;
		for(int i = 1 ; i <= N ; i *= 2) m ++;
		System.out.println(T.solution(N, m));
	}
}
