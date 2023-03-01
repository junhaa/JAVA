import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #8012 한동이는 영업사원!
public class Main {

	static ArrayList<Integer>[] adj;
	static int[][] parent;
	static int[] depth;
	static final int MAX = 16;

	static void DFS(int cur) {
		for (int next : adj[cur]) {
			if (depth[next] == -1) {
				depth[next] = depth[cur] + 1;
				parent[next][0] = cur;
				DFS(next);
			}
		}
	}
	static int LCA(int u, int v) {
		if (depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		int dif = depth[u] - depth[v];
		for (int k = 0; dif != 0; k++) {
			if (dif % 2 == 1)
				u = parent[u][k];
			dif /= 2;
		}

		if (u != v) {
			for (int k = MAX - 1; k >= 0; k--) {
				if (parent[u][k] != -1 && parent[u][k] != parent[v][k]) {
					u = parent[u][k];
					v = parent[v][k];
				}
			}
			return parent[u][0];
		}
		else return u;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		parent = new int[N + 1][MAX];
		depth = new int[N + 1];
		Arrays.fill(depth, -1);
		for (int i = 0; i <= N; i++) {
			Arrays.fill(parent[i], -1);
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adj[A].add(B);
			adj[B].add(A);
		}
		depth[1] = 0;
		DFS(1);
		for (int i = 0; i < MAX - 1; i++) {
			for (int j = 1; j <= N; j++) {
				if (parent[j][i] != -1) {
					parent[j][i + 1] = parent[parent[j][i]][i];
				}
			}
		}
		int answer = 0;
		int M = Integer.parseInt(br.readLine());
		int cur = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i < M ; i ++) {
			int next = Integer.parseInt(br.readLine());
			int lca = T.LCA(cur, next);
			answer += depth[cur] + depth[next] - 2 * depth[lca];
			cur = next;
		}
		System.out.println(answer);
	}
}
