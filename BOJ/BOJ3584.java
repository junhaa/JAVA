import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #3584 가장 가까운 공통 조상
public class Main {

	static ArrayList<Integer>[] adj;
	static int[][] parent;
	static int[] depth;
	static final int MAX = 15;
	
	static void DFS(int cur) {
		for(int next : adj[cur]) {
			depth[next] = depth[cur] + 1;
			parent[next][0] = cur;
			DFS(next);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			adj = new ArrayList[N + 1];
			parent = new int[N + 1][MAX];
			depth = new int[N + 1];
			for(int i = 0 ; i <= N ; i ++) {
				Arrays.fill(parent[i], -1);
				adj[i] = new ArrayList<>();
			}
			HashSet<Integer> set = new HashSet<>();
			HashSet<Integer> setr = new HashSet<>();
			for(int i = 0 ; i < N - 1; i ++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adj[A].add(B);
				if(!setr.contains(A)) {
					set.add(A);
				}
				if(set.contains(B)) {
					set.remove(B);
				}
				setr.add(B);
			}
			int root = -1;
			for(int x : set) {
				root = x;
			}
			depth[root] = 0;
			DFS(root);
			for(int i = 0 ; i < MAX - 1; i ++) {
				for(int j = 1 ; j <= N ; j ++) {
					if(parent[j][i] != -1) {
						parent[j][i + 1] = parent[parent[j][i]][i];
					}
				}
			}
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
				int lca = parent[u][0];
				sb.append(lca + "\n");
			}
			else sb.append(u + "\n");
		}
		System.out.println(sb);
	}
}
