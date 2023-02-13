import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12969 ABC
public class Main {

	static char[] S;
	static int dp[][][][];
	static int N, K;
	
	static int solution(int a, int b, int c, int num, int sum) {
		if(sum > K) return -1;
		if(num == N) {
			if(sum == K) return 1;
			else return -1;
		}
		if(dp[a][b][c][sum] != 0) return dp[a][b][c][sum];
		int tmp = -1;
		S[num + 1] = 'A';
		tmp = Math.max(tmp, solution(a + 1, b, c, num + 1, sum));
		if(tmp != -1) return tmp;
		S[num + 1] = 'B';
		tmp = Math.max(tmp, solution(a, b + 1, c, num + 1, sum + a));
		if(tmp != -1) return tmp;
		S[num + 1] = 'C';
		tmp = Math.max(tmp, solution(a, b, c + 1, num + 1, sum + a + b));
		return dp[a][b][c][sum] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = new char[N + 1];
		dp = new int[N + 1][N + 1][N + 1][K + 1];
		int res = T.solution(0, 0, 0, 0, 0);
		if(res == -1) System.out.println(-1);
		else {
			for(int i = 1 ; i <= N ; i ++) {
				System.out.print(S[i]);
			}
		}
	}
}
