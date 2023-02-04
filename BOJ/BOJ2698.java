import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2698 인접한 비트의 개수 
public class Main {

	static int[][][] dp;
	
	static int solution(int num, int k, int last) {
		if(k < 0) return 0;
		if(dp[num][k][last] != -1) return dp[num][k][last];  
		if(num == 1) {
			if(k == 0)	return dp[num][k][last] = 1;
			else return dp[num][k][last] = 0;
		}
		int tmp = 0;
		tmp += solution(num - 1, k, 0);
		if(last == 1) {
			tmp += solution(num - 1, k - 1, 1);
		}
		else {
			tmp += solution(num - 1, k, 1);
		}
		return dp[num][k][last] = tmp; 
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dp = new int[101][101][2];
		for(int i = 0 ; i <= 100 ; i ++) {
			for(int j = 0 ; j <= 100 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append((T.solution(n, k, 0) + T.solution(n, k, 1)) + "\n");
		}
		System.out.println(sb);
	}
}
