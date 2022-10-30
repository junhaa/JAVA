import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #11438 LCA 2
public class Main {

	static ArrayList<Integer>[] adj;
	static int[][] parent;
	static int[] depth;
	static final int MAX = 18;
	
	
	static void DFS(int cur) {
		for(int next : adj[cur]) {
			if(depth[next] == -1) {
				depth[next] = depth[cur] + 1;
				parent[next][0] = cur;		
				DFS(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		parent = new int[N + 1][MAX];
		depth = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(parent[i], -1);
		}
		Arrays.fill(depth, -1);
		depth[1] = 0;
		for(int i = 1 ; i <= N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		DFS(1);
		for(int i = 0 ; i < MAX - 1 ; i ++) {
			for(int j = 1 ; j <= N ; j ++) {
				if(parent[j][i] != -1) {
					parent[j][i + 1] = parent[parent[j][i]][i];
				}
			}
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(depth[u] < depth[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			int dif = depth[u] - depth[v];
			
			for(int k = 0 ; dif != 0 ; k ++) {
				if(dif % 2 == 1) u = parent[u][k];
				dif /= 2;
			}

			if(u != v) {
				for(int k = MAX - 1 ; k >= 0 ; k --) {
					if(parent[u][k] != -1 && parent[u][k] != parent[v][k]) {
						u = parent[u][k];
						v = parent[v][k];
					}
				}
				u = parent[u][0];
			}
			sb.append(u + "\n");
		}
		System.out.println(sb);
	}
}
