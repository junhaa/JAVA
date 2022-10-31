import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1761 정점들의 거리
class Edge{
	int node;
	int dis;
	public Edge(int node, int dis) {
		this.node = node;
		this.dis = dis;
	}
}
public class Main {

	static final int MAX = 17;
	static ArrayList<Edge>[] adj;
	static int[][] parent;
	static int[] depth, distance;

	
	static void DFS(int cur) { // 트리제작
		for(Edge next : adj[cur]) {
			if(depth[next.node] == -1) {
				distance[next.node] = distance[cur] + next.dis;
				parent[next.node][0] = cur;
				depth[next.node] = depth[cur] + 1;
				DFS(next.node);
			}
		}
	}
	
	static int getDis(int u, int v) { // LCA를 이용한 거리계산
		int node1 = u;
		int node2 = v;
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
			for(int i = MAX - 1 ; i >= 0 ; i --) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			u = parent[u][0];
		}
		return (distance[node1] - distance[u]) + (distance[node2] - distance[u]); 
		
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		parent = new int[N + 1][MAX];
		depth = new int[N + 1];
		distance = new int[N + 1];
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
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end, cost));
			adj[end].add(new Edge(start, cost));
		}
		DFS(1);
		for(int i = 0 ; i < MAX - 1 ; i ++) {
			for(int k = 1 ; k <= N ; k ++) {
				if(parent[k][i] != -1) {
					parent[k][i + 1] = parent[parent[k][i]][i];
				}
			}
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			sb.append(T.getDis(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) + "\n");	
		}
		System.out.println(sb);
	}
}
