import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #11779 최소비용 구하기 2
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	ArrayList<Integer> seq;
	public Edge(int node, int cost, ArrayList<Integer> seq) {
		this.node = node;
		this.cost = cost;
		this.seq = seq;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	static int[] d;
	static StringBuilder solution(int start, int end) { // Dijkstra
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		ArrayList<Integer> min = null;
		d[start] = 0;
		ArrayList<Integer> slist = new ArrayList<>();
		slist.add(start);
		pQ.offer(new Edge(start, d[start], slist));
		
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(d[tmp.node] < tmp.cost) continue;
			for(Edge next : list.get(tmp.node)) {
				int nextD = d[tmp.node] + next.cost;
				if(d[next.node] > nextD) {
					d[next.node] = nextD;
					ArrayList<Integer> nlist = (ArrayList<Integer>) tmp.seq.clone();
					nlist.add(next.node);
					pQ.offer(new Edge(next.node, nextD, nlist));
					if(next.node == end) min = nlist;
				}
			}
		}
		sb.append(d[end]).append('\n').append(min.size()).append('\n');
		for(int x : min) sb.append(x).append(" ");
		return sb;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		d = new int[n + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		for(int i = 0 ; i <= n ; i ++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost, null));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(start, end));
	}
}
