import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #1563 개근상 
public class Main {

	static int dp[][][];
	
	static int solution(int num, int l, int a) {
		if(l == 2 || a == 3) return 0;
		if(dp[num][l][a] != -1) return dp[num][l][a];
		if(num == 0) return 1;
		int tmp = 0;
		tmp += solution(num - 1, l, 0);
		tmp += solution(num - 1, l + 1 , 0);
		tmp += solution(num - 1, l, a + 1);
		tmp %= 1000000;
		return dp[num][l][a] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][2][3];
		for(int i = 0 ; i <= N ; i ++) {
			for(int j = 0 ; j < 2 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(T.solution(N, 0, 0));
	}
}
