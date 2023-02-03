import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2157 여행
public class Main {

	static int[][] dp, adj;
	static int N, M, max = Integer.MIN_VALUE;
	
	public int solution(int cur, int cnt) {
		if(cnt > M) return Integer.MIN_VALUE;
		if(cur == N) return 0;
		if(dp[cur][cnt] != -1) return dp[cur][cnt];
		int tmp = Integer.MIN_VALUE;
		for(int i = cur + 1 ; i <= N ; i ++) {
			if(adj[cur][i] != 0) {
				tmp = Math.max(tmp, solution(i, cnt + 1) + adj[cur][i]);
			}
		}
		return dp[cur][cnt] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][M + 1];
		adj = new int[N + 1][N + 1];
		for(int i = 0 ; i <= N ; i ++) { 
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(start > end) continue;
			adj[start][end] = Math.max(adj[start][end], cost);
		}
		System.out.println(T.solution(1, 1));
	}
}
