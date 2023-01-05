import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17399 트리의 외심
public class Main {

	static ArrayList<Integer>[] adj;
	static final int LOG = 17;
	static int[] depth;
	static int[][] parent;
	
	public void DFS(int cur) {
		for(int next : adj[cur]) {
			if(depth[next] == -1) {
				depth[next] = depth[cur] + 1;
				parent[next][0] = cur;
				DFS(next);
			}
		}
	}
	public int LCA(int u, int v) {
		
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		
		int dif = depth[u] - depth[v];
		for(int i = 0 ; dif != 0 ; i ++) { // 높이 맞추기
			if(dif % 2 == 1) u = parent[u][i];
			dif /= 2;
		}
		
		if(u != v) {
			for(int i = LOG - 1 ; i >= 0 ; i --) { // LCA 탐색
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			u = parent[u][0];
		}
		return u;
	}	
	
	public int getCenter(int lca, int u, int v, int k) {
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		int dif = 0;
		if(v == lca) {
			dif = depth[u] - depth[v];
		}
		
		else {
			dif = (depth[v] + depth[u] - 2 * depth[lca]);
		}
		if(dif % 2 == 1) return -1;
		dif /= 2;
		int tmpDif = dif;
		
		for(int i = 0 ; dif != 0 ; i ++) { 
			if(dif % 2 == 1) u = parent[u][i];
			dif /= 2;
		}
		int center = u;
		
		int lcaK = LCA(center, k);
		if(lcaK == center || lcaK == k) {
			if((Math.max(depth[center], depth[k]) - Math.min(depth[center], depth[k]))  == tmpDif) {
				return center;
			}
			else return -1;
		}
		else {
			if((depth[center] + depth[k] - 2 * depth[lcaK]) == tmpDif) {
				return center;
			}
			else return - 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N];
		parent = new int[N][LOG];
		depth = new int[N];
		Arrays.fill(depth, -1);
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
			Arrays.fill(parent[i], -1);
		}
		StringTokenizer st;
		for(int i = 1 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			adj[start].add(end);
			adj[end].add(start);
		}
		depth[0] = 0;
		
		T.DFS(0);
		
		for(int i = 0 ; i < LOG - 1 ; i ++) {
			for(int k = 1 ; k < N ; k ++) {
				if(parent[k][i] != -1) {
					parent[k][i + 1] = parent[parent[k][i]][i];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			int answer = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			answer = Math.max(answer, T.getCenter(T.LCA(a,b), a, b, c));
			answer = Math.max(answer, T.getCenter(T.LCA(b, c), b, c, a));
			answer = Math.max(answer, T.getCenter(T.LCA(c, a), c, a, b));
			if(answer != -1) answer ++;
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
