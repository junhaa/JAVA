import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1234 크리스마스 트리
public class Main {

	static long[][][][] dp;
	static int[] fact = new int[11];
	static int N;
	
	static int factorial(int i) {
		if(i == 1) return fact[1] = 1;
		return fact[i] = i * factorial(i - 1);
	}
	
	static long getMul(int num, int n) {
		long mtmp = 1;
		for(int i = 0 ; i < num ; i ++) {
			mtmp *= fact[n / num];
		}
		return fact[n] / mtmp;
	}
	
	static long solution(int n, int r, int g, int b) {
		if(r < 0 || g < 0 || b < 0) return 0;
		if(n == N) { 
			return 1;
		}
		if(dp[n][r][g][b] != -1) return dp[n][r][g][b];
		long tmp = 0;
		tmp += solution(n + 1, r - (n + 1), g, b);
		tmp += solution(n + 1, r, g - (n + 1), b);
		tmp += solution(n + 1, r, g, b - (n + 1));
		if((n + 1) % 2 == 0) {
			tmp += solution(n + 1, r - ((n + 1) / 2), g - ((n + 1) / 2), b) * getMul(2, n + 1);
			tmp += solution(n + 1, r - ((n + 1) / 2), g, b - ((n + 1) / 2)) * getMul(2, n + 1);
			tmp += solution(n + 1, r, g - ((n + 1) / 2), b - ((n + 1) / 2)) * getMul(2, n + 1);
		}
		if((n + 1) % 3 == 0) {
			tmp += solution(n + 1, r - ((n + 1) / 3), g - ((n + 1) / 3), b - ((n + 1) / 3)) * getMul(3, n + 1);
		}
		return dp[n][r][g][b] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		dp = new long[N + 1][r + 1][g + 1][b + 1];
		fact[0] = 1;
		T.factorial(N);
		for(int i = 0 ; i <= N ; i ++) {
			for(int j = 0 ; j <= r ; j ++) {
				for(int k = 0 ; k <= g ; k ++) {
					Arrays.fill(dp[i][j][k], -1);
				}
			}
		}
		System.out.println(T.solution(0, r, g, b));
	}
}
