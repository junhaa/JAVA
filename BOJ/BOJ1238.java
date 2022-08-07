import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1238 ÆÄÆ¼
class Edge implements Comparable<Edge>{
	int current;
	int cost;
	public Edge(int current, int cost) {
		this.current = current;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[] d, sum;
	static int N;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	

	static int dijkstra(int s, int e) {
		//if(s == e) return 0;
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		d[s] = 0;
		pQ.offer(new Edge(s, 0));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(d[tmp.current] < tmp.cost) continue;
			for(Edge x : list.get(tmp.current)) {
				if(d[x.current] > d[tmp.current] + x.cost) {
					d[x.current] = d[tmp.current] + x.cost;
					pQ.offer(x);
				}
			}
		}
		return d[e];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = Integer.MIN_VALUE;
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		d = new int[N + 1];
		sum = new int[N + 1];
		for(int i = 0 ; i <= N ; i ++) {
			d[i] = Integer.MAX_VALUE;
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost));
		}
		T.dijkstra(X, 0);
		for(int i = 1 ; i <= N ; i ++) {
			sum[i] = d[i];
			d[i] = Integer.MAX_VALUE;
		}
		for(int i = 1; i <= N ; i ++) {
			sum[i] += T.dijkstra(i, X);
			for(int k = 1 ; k <= N ; k ++) {
				d[k] = Integer.MAX_VALUE;
			}
			answer = Math.max(answer, sum[i]);
		}
		System.out.println(answer);
	}
}
