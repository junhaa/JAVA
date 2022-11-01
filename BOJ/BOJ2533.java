import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2533 사회망 서비스(SNS)
public class Main {

	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int[][] dp;
	static int[] depth;
	
	static void makeTree(int cur) {
		for(int next : adj[cur]) {
			if(depth[next] == -1) {
				depth[next] = depth[cur] + 1;
				makeTree(next);
			}
		}
	}
	
	static int DFS(int idx, int num) {
		visited[idx] = true;
		int tmp = 0;
		if(num == 1) {
			tmp++;
			for(int next : adj[idx]) {
				if(depth[next] < depth[idx]) continue;
				int a = 10000000, b = 10000000;
				if(dp[next][0] == 10000000) a = DFS(next, 0);
				else a = dp[next][0];
				if(dp[next][1] == 10000000) b = DFS(next, 1);
				else b = dp[next][1];
				tmp += Math.min(a, b);
			}
		}
		else {
			for(int next : adj[idx]) {
				if(depth[next] < depth[idx]) continue;
				if(dp[next][1] == 10000000) tmp += DFS(next, 1);
				else tmp += dp[next][1];
			}
		}
		return dp[idx][num] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		for(int i = 1 ; i <= N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < N - 1; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj[start].add(end);
			adj[end].add(start);
		}
		makeTree(1);
		for(int i = 1 ; i <= N ; i ++) {
			Arrays.fill(dp[i], 10000000);
		}
		int min = DFS(1, 0);
		visited = new boolean[N + 1];
		dp = new int[N + 1][2];
		for(int i = 1 ; i <= N ; i ++) {
			Arrays.fill(dp[i], 10000000);
		}
		System.out.println(Math.min(min, DFS(1,1)));
	}
}
