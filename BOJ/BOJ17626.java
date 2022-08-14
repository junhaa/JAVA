import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #17626 Four Squares
public class Main {
	static int[] dp;
	
	/* -- stack overflow --
	static int solution(int num) {
		if(dp[num] != 0) return dp[num];
		int get = Integer.MAX_VALUE;
		for(int i = 1 ; i * i <= num ; i ++) {
			get = Math.min(get, solution(num - i * i));
		}
		return dp[num] = get + 1; 
	}
	*/
	
	static int solution(int n) {
		int num = n;
		for(int i = 2 ; i <= n ; i ++) {
			int min = Integer.MAX_VALUE;
			for(int k = 1 ; k * k <= i ; k ++) {
				min = Math.min(min, dp[i - k * k]);
			}
			dp[i] = min + 1;
		}
		return dp[n];
	}

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		dp[1] =1;
		System.out.println(T.solution(n));
	}
}
