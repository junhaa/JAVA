import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #6086 최대 유량
class Edge{
	Edge dual;
	int to, c, f = 0;
	public Edge(int to, int c) {
		this.to = to;
		this.c = c;
	}
	public int spare() {
		return c - f;
	}
	public void addFlow(int f1) {
		f += f1;
		dual.f -= f1;
	}
}
public class Main {

	static ArrayList<Edge>[] adj = new ArrayList[52];
	
	
	public int toI(char x) {
		if(x > 'Z') return x - 'a' + 26;
		else return x - 'A';
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < 52 ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int u = T.toI(st.nextToken().charAt(0));
			int v = T.toI(st.nextToken().charAt(0));
			int w = Integer.parseInt(st.nextToken());
			Edge e1 = new Edge(v, w);
			Edge e2 = new Edge(u, w);
			e1.dual = e2;
			e2.dual = e1;
			adj[u].add(e1);
			adj[v].add(e2);
		}
		int total = 0;
		int S = T.toI('A');
		int E = T.toI('Z');
		while(true) {
			int[] prev = new int[52];
			Edge[] path = new Edge[52];
			Arrays.fill(prev, -1);
			Queue<Integer> Q = new LinkedList<>();
			Q.offer(S);
			while(!Q.isEmpty() && prev[E] == -1) {
				int cur = Q.poll();
				for(Edge e : adj[cur]) {
					int next = e.to;
					if(e.spare() > 0 && prev[next] == -1) {
						Q.offer(next);
						prev[next] = cur;
						path[next] = e;
						if(next == E) break;
					}
				}
			}
			if(prev[E] == -1) break;
			
			int flow = Integer.MAX_VALUE;
			for(int i = E ; i != S ; i = prev[i]) {
				flow = Math.min(flow, path[i].spare());
			}
			for(int i = E ; i != S ; i = prev[i]) {
				path[i].addFlow(flow);
			}
			total += flow;
		}
		System.out.println(total);
	}
}
