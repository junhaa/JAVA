import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1753 최단경로
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
	static int[] d; // 최소 거리
	static boolean[] visited; // 방문 여부
	static ArrayList<ArrayList<Edge>> list; // 연결된 노드 리스트
	static StringBuilder solution(int V, int K) { // Dijkstra
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(K,0));
		d[K] = 0;
		while(!pQ.isEmpty()) {
			Edge now = pQ.poll();
			if(!visited[now.node]) {
				visited[now.node] = true;
				for(Edge x : list.get(now.node)) {
					if(d[x.node] > d[now.node] + x.cost) {
						d[x.node] = d[now.node] + x.cost;	
						pQ.offer(new Edge(x.node, d[x.node]));
					}
				}
			}
		}
		for(int i = 1 ; i <= V ; i ++) {
			if(d[i] == 10000000) sb.append("INF").append('\n');
			else sb.append(d[i]).append('\n');
		}
		return sb;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수 
		int K = Integer.parseInt(br.readLine()); // 시작점
		d = new int[V + 1];
		visited = new boolean[V + 1];
		list = new ArrayList<>();
		Arrays.fill(d, 10000000);
		for(int i = 0 ; i <= V ; i ++) {
			list.add(new ArrayList<Edge>());	
		}
		for(int i = 0 ; i < E ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end,weight));
		}
		
		System.out.println(T.solution(V, K));
	}
}
