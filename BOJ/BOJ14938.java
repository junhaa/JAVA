import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #14938 서강그라운드
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
	static int[] item, d;
	static int n, m;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	
	static int dijkstra(int i) {
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		int getItem = 0;
		d[i] = 0;
		getItem += item[i];
		visited[i] = true;
		pQ.offer(new Edge(i,0));
		
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			for(Edge next : list.get(tmp.node)) {
				if(d[tmp.node] < tmp.cost) continue;
				int nextD = d[tmp.node] + next.cost;
				if(d[next.node] > nextD) {
					if(!visited[next.node] && nextD <= m) {
						getItem += item[next.node];
						visited[next.node] = true;
					}
					d[next.node] = nextD;
					pQ.offer(new Edge(next.node, nextD));
				}
			}
		}
		return getItem;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = Integer.MIN_VALUE;
		n = Integer.parseInt(st.nextToken()); // 지역의 개수
		m = Integer.parseInt(st.nextToken()); // 예은이의 수색범위 
		int r = Integer.parseInt(st.nextToken()); // 길의 개수
		item = new int[n + 1];
		d = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <= n ; i ++) {
			list.add(new ArrayList<>());
		}
		for(int i = 1 ; i <= n ; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < r ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost));
			list.get(end).add(new Edge(start, cost));
		}
		for(int i = 1 ; i <= n ; i ++) {
			Arrays.fill(d, Integer.MAX_VALUE);
			answer = Math.max(answer, dijkstra(i));
		}
		System.out.println(answer);
	}
}
