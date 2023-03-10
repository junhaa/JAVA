import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #13244 Tree
public class Main {

	static int[] parent;
	static boolean[] visited;
	static int N;
	static boolean flag;
	static ArrayList<Integer>[] adj;
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) {
			parent[fb] = fa;
		}
		else parent[fa] = fb;
	}
	
	static void DFS(int cur, int pre) { // true = tree false = graph
		visited[cur] = true;
		union(cur, pre);
		for(int next : adj[cur]) {
			if(!flag) return;
			if(next == pre) continue;
			if(visited[next]) {
				flag = false;
				return;
			}
			DFS(next, cur);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N];
			visited = new boolean[N];
			int M = Integer.parseInt(br.readLine());
			adj = new ArrayList[N];
			flag = true;
			for(int i = 0 ; i < N ; i ++) {
				adj[i] = new ArrayList<>();
				parent[i] = i;
			} 
			for(int i = 0 ; i < M ; i ++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken()) - 1;
				int n2 = Integer.parseInt(st.nextToken()) - 1;
				adj[n1].add(n2);
				adj[n2].add(n1);
			}
			T.DFS(0, 0);
			int p = parent[0];
			if(N > 1) {
				for(int i = 0 ; i < N ; i ++) {
					if(parent[i] != p) {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				sb.append("tree\n");
			}
			else sb.append("graph\n");
		}
		System.out.println(sb);
	}
}
