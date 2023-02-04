import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #10422 괄호 
public class Main {
	
	static int dp[][] = new int[5001][5001]; // num , o
	
	static int solution(int num, int o) {
		if(o == 0 && num % 2 == 1) return dp[num][o] = 0;
		if(dp[num][o] != -1) return dp[num][o];
		if(num < o) return dp[num][o] = 0;
		if(num == o) return dp[num][o] = 1;
		int tmp = 0;
		tmp += solution(num - 1, o + 1);
		if(o > 0) tmp += solution(num - 1, o - 1);
		tmp %= 1000000007;
		return dp[num][o] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int i = 0 ; i <= 5000 ; i ++) {
			Arrays.fill(dp[i],-1);
		}
		while(test -- > 0) {
			sb.append(T.solution(Integer.parseInt(br.readLine()), 0) +"\n");
		}
		System.out.println(sb);
	}
}
