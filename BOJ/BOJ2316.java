import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge{
	int to, c, f = 0;
	Edge dual;
	public Edge(int to, int c) {
		this.to = to;
		this.c = c;
	}
	public int spare() {
		return c - f;
	}
	void addFlow(int f1) {
		f += f1;
		dual.f -= f1;
	}
}
public class Main {
	
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N * 2];
		for(int i = 0 ; i < N * 2 ; i ++) adj[i] = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			Edge e1 = new Edge(i * 2 + 1, 1);
			Edge e2 = new Edge(i * 2, 0);
			e1.dual = e2;
			e2.dual = e1;
			adj[i * 2].add(e1);
			adj[i * 2 + 1].add(e2);
		}
		for(int i = 0 ; i < P ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = (Integer.parseInt(st.nextToken()) - 1) * 2 + 1;
			int end = (Integer.parseInt(st.nextToken()) - 1) * 2;
			Edge e1 = new Edge(end, 1);
			Edge ee1 = new Edge(start, 0);
			Edge e2 = new Edge(start - 1, 1);
			Edge ee2 = new Edge(end + 1, 0);
			e1.dual = ee1;
			ee1.dual = e1;
			e2.dual = ee2;
			ee2.dual = e2;
			adj[start].add(e1);
			adj[end].add(ee1);
			adj[end + 1].add(e2);
			adj[start - 1].add(ee2);
		}
		int total = 0, S = 1, E = 2;
		while(true) {
			int[] pre = new int[N * 2];
			Arrays.fill(pre, -1);
			Edge[] path = new Edge[N * 2];
			Queue<Integer> Q = new LinkedList<>();
			Q.offer(S);
			while(!Q.isEmpty() && pre[E] == -1) {
				int cur = Q.poll();
				for(Edge e : adj[cur]) {
					int next = e.to;
					if(e.spare() > 0 && pre[next] == -1) {
						Q.offer(next);
						pre[next] = cur;
						path[next] = e;
						if(next == E) break;
					}
				}
			}
			if(pre[E] == -1) break;
			int flow = Integer.MAX_VALUE;
			for(int i = E ; i != S ; i = pre[i]) {
				flow = Math.min(flow, path[i].spare());
			}
			for(int i = E ; i != S ; i = pre[i]) {
				path[i].addFlow(flow);
			}
			total += flow;
			
		}
		System.out.println(total);
	}
		
}
