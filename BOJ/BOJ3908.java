import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #3908 서로 다른 소수의 합
public class Main {

	static int dp[][][];
	static boolean[] ch;
	
	static int solution(int n, int k, int last) {
		if(n < 0) return 0;
		if(k == 0) {
			if(n == 0) return 1;
			else return 0;
		}
		if(dp[k][n][last] != -1) return dp[k][n][last];
		int tmp = 0;
		for(int i = last + 1 ; i <= n ; i ++) {
			if(!ch[i]) {
				tmp += solution(n - i, k - 1, i);
			}
		}
		return dp[k][n][last] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dp = new int[15][1121][1121];
		ch = new boolean[1121];
		StringTokenizer st;
		ch[1] = true;
		ch[0] = true;
		for(int i = 1 ; i <= 14 ; i ++) {
			for(int j = 0 ; j <= 1120 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		for(int i = 2 ; i <= Math.sqrt(1120) ; i ++) {
			for(int k = i * i ; k <= 1120 ; k += i) {
				ch[k] = true;
			}
		}
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			sb.append(T.solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0) + "\n");
		}
		System.out.println(sb);
	}

}
