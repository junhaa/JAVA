import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #13265 색칠하기
public class Main {

	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x]) return x;
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
	
	static boolean BFS(int[] node, ArrayList<Integer>[] adj, int[] num) {
		Queue<Integer> Q = new LinkedList<>();
		HashSet<Integer> set = new HashSet<>();
		boolean[] visited = new boolean[node.length];
		for(int i = 0 ; i < node.length ; i ++) {
			int tmpf = find(i);
			if(!set.contains(tmpf)) {
				Q.offer(i);
				num[i] = 1;
				visited[i] = true;
				set.add(tmpf);
			}
		}
		int L = 1;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				int tmp = Q.poll();
				for(int next : adj[tmp]) {
					if(visited[next]) {
						if((num[next] + num[tmp]) % 2 == 0) return false;
					}
					else {
						visited[next] = true;
						num[next] = L + 1;
						Q.offer(next);
					}
				}
			}
			L ++;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] node = new int[n];
			int[] num = new int[n];
			parent = new int[n];
			ArrayList<Integer>[] adj = new ArrayList[n];
			for(int i = 0 ; i < n ; i ++) {
				adj[i] = new ArrayList<>();
				parent[i] = i;
			}
			for(int i = 0 ; i < m ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				T.union(start, end);
				adj[start].add(end);
				adj[end].add(start);
			}
			if(T.BFS(node, adj, num)) sb.append("possible\n");
			else sb.append("impossible\n");
		}
		System.out.println(sb);
	}
}
