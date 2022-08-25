import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #5972 택배 배송
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	
	static ArrayList<Edge>[] list; 
	static int[] d;

	static void dijkstra(int N) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		d[1] = 0;
		pQ.offer(new Edge(1,0));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(tmp.cost > d[tmp.node]) continue;
			for(Edge next : list[tmp.node]) {
				int nextD = d[tmp.node] + next.cost;
				if(nextD < d[next.node]) {
					d[next.node] = nextD;
					pQ.offer(new Edge(next.node, nextD));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		d = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, cost));
			list[end].add(new Edge(start, cost));
		}
		T.dijkstra(N);
		System.out.println(d[6]);
	}
}
