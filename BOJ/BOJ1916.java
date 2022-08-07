import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1916 최소비용 구하기
class Edge implements Comparable<Edge>{
	int end;
	int cost;
	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[] mcost; // 최소비용
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	
	static int solution(int S, int E) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(S, 0));
		mcost[S] = 0;
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			for(Edge o : list.get(tmp.end)) {
				if(mcost[o.end] > mcost[tmp.end] + o.cost) { 
					mcost[o.end] = mcost[tmp.end] + o.cost;
					pQ.offer(o);
				}
			}
		}
		return mcost[E];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		mcost = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			mcost[i] = Integer.MAX_VALUE;
		}
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost));
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(S, E));
	}

}
