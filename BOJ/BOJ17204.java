import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 17204 죽음의 게임
public class Main {
	static int[] parent;
	static ArrayList<Integer>[] adj;
	static int K;
	static boolean[] visited;
	
	static int find(int x) {
		if(x == parent[x]) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) parent[fb] = fa;
		else parent[fa] = fb;
	}
	
	static int BFS() {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(K);
		visited[K] = true;
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				int tmp = Q.poll();
				if(tmp == 0) return L;
				for(int next : adj[tmp]) {
					if(!visited[next]) {
						Q.offer(next);
						visited[next] = true;
					}
				}
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		parent = new int[N];
		adj = new ArrayList[N];
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i ++) {
			parent[i] = i;
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			T.union(i, tmp);
			adj[tmp].add(i);
		}
		if(T.find(0) != T.find(K)) {
			System.out.println(-1);
			return;
		}
		System.out.println(T.BFS());
	}
}
