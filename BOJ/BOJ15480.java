import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #15480 LCA와 쿼리
public class Main {

	static final int LOG = 17;
	static ArrayList<Integer>[] list;
	static int[] depth;
	static int[][] parent;
	
	static void DFS(int cur) {
		for(int next : list[cur]) {
			if(depth[next] == -1) {
				parent[next][0] = cur;
				depth[next] = depth[cur] + 1;
				DFS(next);
			}
		}
	}
	
	static int LCA(int u, int v) {
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		int dif = depth[u] - depth[v];
		
		for(int i = 0 ; dif != 0 ; i ++) {
			if(dif % 2 == 1) u = parent[u][i];
			dif /= 2;
		}
		if(u != v) {
			for(int i = LOG - 1 ; i >= 0 ; i --) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			return parent[u][0];
		}
		else {
			return u;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		list = new ArrayList[N + 1];
		depth = new int[N + 1];
		parent = new int[N + 1][LOG];
		for(int i = 1 ; i <= N ; i ++) list[i] = new ArrayList<>();
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		int M = Integer.parseInt(br.readLine());
		Arrays.fill(depth, -1);
		for(int k = 0 ; k <= N ; k ++) {
			Arrays.fill(parent[k], -1);
		}
		
		depth[1] = 0;
		T.DFS(1);
		for(int j = 0 ; j < LOG - 1 ; j ++) {
			for(int k = 1 ; k <= N ; k ++) {
				if(parent[k][j] != -1) {
					parent[k][j + 1] = parent[parent[k][j]][j];
				}
				}
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int root = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int lca1 = T.LCA(root, node1);
			int lca2 = T.LCA(root, node2);
			int lca3 = T.LCA(node1, node2);
			
			if(depth[lca1] > depth[lca2] && depth[lca1] > depth[lca3]) {
				sb.append(lca1 + "\n");
			}
			else if (depth[lca2] > depth[lca1] && depth[lca2] > depth[lca3]) {
				sb.append(lca2 + "\n");
			}
			else {
				sb.append(lca3 + "\n");
			}
			
		}
		System.out.println(sb);
	}
}
