import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #12996 Acka
public class Main {

	static int[][][][] dp;
	
	static int solution(int S, int a, int b, int c) {
		if(S < 0) return 0;
		if(a < 0 || b < 0 || c < 0) return 0;
		if(a == 0 && b == 0 && c == 0) { 
			if(S == 0) return 1;
			else return 0;
		}
		if(dp[S][a][b][c] != -1) return dp[S][a][b][c];
		long tmp = 0;
		tmp += solution(S - 1, a - 1, b, c);
		tmp += solution(S - 1, a , b - 1, c);
		tmp += solution(S - 1, a, b, c - 1);
		tmp += solution(S - 1, a - 1, b - 1, c);
		tmp += solution(S - 1, a - 1, b, c - 1);
		tmp += solution(S - 1, a, b - 1, c - 1);
		tmp += solution(S - 1, a - 1, b - 1, c - 1);
		tmp %= 1000000007;
		return dp[S][a][b][c] = (int)tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		dp = new int[S + 1][a + 1][b + 1][c + 1];
		for(int i = 0 ; i <= S ; i ++) {
			for(int j = 0 ; j <= a ; j ++) {
				for(int k = 0 ; k <= b ; k ++) {
					Arrays.fill(dp[i][j][k], -1);
				}
			}
		}
		System.out.println(T.solution(S, a, b, c));
	}
}
