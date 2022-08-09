import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1719 ец╧Х
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	int first;
	public Edge(int node, int cost, int first) {
		this.node = node;
		this.cost = cost;
		this.first = first;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	static int[] d, df;
	static StringBuilder sb = new StringBuilder();
	
	static void dijkstra(int i) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		d[i] = 0;
		for(Edge x : list.get(i)) {
			d[x.node] = x.cost;
			df[x.node] = x.node;
			pQ.offer(new Edge(x.node, x.cost, x.node));
		}
		
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(tmp.cost > d[tmp.node]) continue;
			for(Edge next : list.get(tmp.node)) {
				int nextD = d[tmp.node] + next.cost;
				if(d[next.node] > nextD) {
					d[next.node] = nextD;
					df[next.node] = tmp.first;
					pQ.offer(new Edge(next.node, nextD, tmp.first));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i <= n ; i ++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost, 0));
			list.get(end).add(new Edge(start, cost, 0));
		}
		d = new int [n + 1];
		df = new int[n + 1];
		for(int i = 1 ; i <= n ; i++) {
			Arrays.fill(d, Integer.MAX_VALUE);
			T.dijkstra(i);
			for(int k = 1 ; k <= n ; k ++) {
				if(k == i) sb.append("- ");
				else sb.append(df[k]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
