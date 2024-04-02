import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #15989 1, 2, 3 더하기 4
public class Main {
	static long[][] dp = new long[4][10001];
	static StringBuilder sb = new StringBuilder();
	static long recursive(int cur, int last){
		if(cur < 0){
			return 0;
		}
		if(cur == 0){
			return 1;
		}
		if(dp[last][cur] != -1) return dp[last][cur];
		int sum = 0;
		for(int i = last ; i <= 3 ; i ++){
			sum += recursive(cur - i, i);
		}
		return dp[last][cur] = sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < 4 ; i ++) {
			Arrays.fill(dp[i], -1);
		}
		while(T -- > 0){
			int n = Integer.parseInt(br.readLine());
			sb.append(recursive(n, 1));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
