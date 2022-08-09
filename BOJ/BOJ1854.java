import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1854 K번째 최단경로 찾기
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.cost = cost;
		this.node = node;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	static PriorityQueue<Integer>[] d;
	static int[][] arr;
	static StringBuilder solution(int n, int k) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(1, 0));
		d[1].add(0);
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			for(Edge x : list.get(tmp.node)) {
				if(d[x.node].size() < k) {
					d[x.node].add((tmp.cost + x.cost));
					pQ.add(new Edge(x.node, tmp.cost + x.cost));
				}
				else if(d[x.node].peek()> (tmp.cost + x.cost)) {
					d[x.node].poll();
					d[x.node].add((tmp.cost + x.cost));
					pQ.add(new Edge(x.node, tmp.cost + x.cost));
				}
			}
		}
		for(int i = 1 ; i <= n ; i ++) {
			if(d[i].size() == k) sb.append(d[i].poll()).append('\n');
			else sb.append("-1").append('\n');
		}
		return sb;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도시들의 개수
		int m = Integer.parseInt(st.nextToken()); // 도로의 수
		int k = Integer.parseInt(st.nextToken()); 
		arr = new int[n + 1][k];
		d = new PriorityQueue [n + 1];
		for(int i = 0 ; i <= n ; i ++) {
			list.add(new ArrayList<>());
			d[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end,cost));
		}
		System.out.println(T.solution(n, k));
	}
}
