import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9095 1,2,3 ´õÇÏ±â
public class Main {
	static int dp[];
	
	static int solution(int n) {
		if(dp[n] != 0) return dp[n];
		if(n == 1) return dp[1] = 1;
		else if(n == 2) return dp[2] = 2;
		else if(n == 3) return dp[3] = 4;
		else return dp[n] = solution(n - 1) + solution(n - 2) + solution(n - 3);
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test-- >0) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) { 
				sb.append('0').append('\n');
				continue;
			}
			dp = new int[n + 1];
			sb.append(T.solution(n)).append('\n');
		}
		System.out.println(sb);
	}
}
