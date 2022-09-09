import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ #1256 »çÀü
public class Main {

	static int [][] dp;
	static int N, M, K;
	
	static int Combi(int n, int r) {
		if(dp[n][r] != 0) return dp[n][r];
		if(n == r || r == 0) return 1;
		return dp[n][r] = Math.min(Combi(n - 1, r) + Combi(n - 1, r - 1), 1000000001);
	}
	
	static StringBuilder solution(int num, int a, int K) {
		StringBuilder sb = new StringBuilder();
		if(Combi(num, a) < K) {
			sb.append("-1");
			return sb;
		}
		while(true) {
			if(a == 0) {
				for(int i = num ; i > 0 ; i --) {
					sb.append("z");
				}
				return sb;
			}
			if(num == a) {
				for(int i = num ; i > 0 ; i --) {
					sb.append("a");
				}
				return sb;
			}
			if(Combi(num - 1, a - 1) >= K) {
				num --;
				a --;
				sb.append("a");
			}
			else if(Combi(num - 1, a - 1) < K) {
				K -= Combi(num - 1, a - 1);
				num --;
				sb.append("z");
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dp = new int[N + M + 1][N + M + 1];
		T.Combi(N + M, N);
		System.out.println(T.solution(N + M, N, K));
		
	}
}
