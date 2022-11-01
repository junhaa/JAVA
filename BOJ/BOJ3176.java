import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #3176 도로 네트워크
class Edge{
	int node;
	int distance;
	public Edge(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}
public class Main {

	static int[][] parent, min, max;
	static int[] depth;
	static final int LOG = 18;
	static ArrayList<Edge>[] list;
	static int minD, maxD;
	
	
	static void DFS(int cur) {
		for(Edge next : list[cur]) {
			if(depth[next.node] == -1) {
				depth[next.node] = depth[cur] + 1;
				parent[next.node][0] = cur;
				min[next.node][0] = next.distance;
				max[next.node][0] = next.distance;
				DFS(next.node);
			}
		}
	}
	
	public void LCA(int u, int v) {
		minD = Integer.MAX_VALUE;
		maxD = Integer.MIN_VALUE;
		int minU = Integer.MAX_VALUE;
		int minV = Integer.MAX_VALUE;
		int maxU = Integer.MIN_VALUE;
		int maxV = Integer.MIN_VALUE;
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		int dif = depth[u] - depth[v];
		for(int i = 0 ; dif != 0 ; i ++) {
			if(dif % 2 == 1) {
				minU = Math.min(minU, min[u][i]);
				maxU = Math.max(maxU, max[u][i]);
				u = parent[u][i];
			}
			dif /= 2;
		}
		
		if(u != v) {
			for(int i = LOG - 1 ; i >= 0 ; i --) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					minU = Math.min(minU, min[u][i]);
					maxU = Math.max(maxU, max[u][i]);
					minV = Math.min(minV, min[v][i]);
					maxV = Math.max(maxV, max[v][i]);
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			minU = Math.min(minU, min[u][0]);
			maxU = Math.max(maxU, max[u][0]);
			minV = Math.min(minV, min[v][0]);
			maxV = Math.max(maxV, max[v][0]);
		}
		
		minD = Math.min(minV, minU);
		maxD = Math.max(maxV, maxU);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		list = new ArrayList[N + 1];
		depth = new int[N + 1];
		parent = new int[N + 1][LOG];
		min = new int[N + 1][LOG];
		max = new int[N + 1][LOG];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 1 ; i <= N ; i ++ ) {
			Arrays.fill(parent[i], -1);
			//Arrays.fill(min[i], Integer.MAX_VALUE);
			//Arrays.fill(max[i], Integer.MIN_VALUE);
		}
		Arrays.fill(depth, -1);
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, cost));
			list[end].add(new Edge(start, cost));
		}
		depth[1] = 0;
		DFS(1);
		for(int i = 0 ; i < LOG - 1 ; i ++) {
			for(int k = 1 ; k <= N ; k ++) {
				if(parent[k][i] != -1) {
					parent[k][i + 1] = parent[parent[k][i]][i];
					min[k][i + 1] = Math.min(min[k][i], min[parent[k][i]][i]);
					max[k][i + 1] = Math.max(max[k][i], max[parent[k][i]][i]);
				}
			}
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			T.LCA(u, v);
			sb.append(minD + " " + maxD + "\n");
		}
		System.out.println(sb);
	}
}
