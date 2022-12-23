import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #13511 트리와 쿼리 2
class Edge{
	int node, dis;
	public Edge(int node, int dis) {
		this.node = node;
		this.dis = dis;
	}
}
public class Main {

	static final int LOG = 18;
	static int[] depth;
	static long[] distance;
	static int[][] parent;
	static ArrayList<Edge>[] list;
	
	
	static void DFS(int cur) {
		for(Edge next : list[cur]) {
			if(depth[next.node] == -1) {
				distance[next.node] = distance[cur] + next.dis;
				parent[next.node][0] = cur;
				depth[next.node] = depth[cur] + 1;
				DFS(next.node);
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
			for(int i = LOG - 1 ; i >= 0 ; i -- ) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
		}
        else {
            return u;
        }
		u = parent[u][0];
		return u;
	}
		
	static long getDis(int u, int v) {
		int lca = LCA(u, v);
		return distance[u] - distance[lca] + distance[v] - distance[lca];
	}
	
	static int getNode(int u, int v, int num) {
		int lca = LCA(u,v);
		if(num == -1) return u;
		if(num <= depth[u] - depth[lca]) {
			int dif = num;
			for(int i = 0 ; dif != 0 ; i ++) {
				if(dif % 2 == 1) u = parent[u][i];
				dif /= 2;
			}
			return u;
		}
		else {
			int dif = (depth[v] - depth[lca]) - (num - (depth[u] - depth[lca]));
			for(int i = 0 ; dif != 0 ; i ++) {
				if(dif % 2 == 1) v = parent[v][i];
				dif /= 2;
			}
			return v;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		parent = new int[N + 1][LOG];
		depth = new int[N + 1];
		distance = new long[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
			Arrays.fill(parent[i], -1);
		}
		Arrays.fill(depth, -1);
		depth[1] = 0;
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end ,cost));
			list[end].add(new Edge(start, cost));
		}
		T.DFS(1);
		for(int i = 0 ; i < LOG - 1 ; i ++) {
			for(int k = 1 ; k <= N ; k ++) {
				if(parent[k][i] != -1) {
					parent[k][i + 1] = parent[parent[k][i]][i];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				sb.append(T.getDis(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
			}
			else{
				sb.append(T.getNode(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1)).append("\n");
			}
		}
		System.out.println(sb);
	}
}
