import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1504 특정한 최단 경로
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	boolean node1;
	boolean node2;
	public Edge(int node, int cost, boolean node1, boolean node2) {
		this.node = node;
		this.cost = cost;
		this.node1 = node1;
		this.node2 = node2;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
} 
public class Main {
	static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
	static int[] d, d1, d2, d3;
	
	static int solution(int v1, int v2, int N) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		if(v1 == 1) {
			pQ.offer(new Edge(1, 0, true, false));
			d1[1] = 0;
		}
		else { 
			pQ.offer(new Edge(1, 0, false, false));
			d[1] = 0;
		}
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			for(Edge next : list.get(tmp.node)) {
				
				if(tmp.node1 && !tmp.node2) { // v1 만 지나간 경우
					if(tmp.cost > d1[tmp.node]) continue;
					int nextD = d1[tmp.node] + next.cost;
					if(next.node == v2 && d3[next.node] > nextD) {
						d3[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, true, true));
					}
					else if(d1[next.node] > nextD) {
						d1[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, true, false));
					}
				}
				else if(tmp.node2 && !tmp.node1) { // v2 만 지나간 경우
					if(tmp.cost > d2[tmp.node]) continue;
					int nextD = d2[tmp.node] + next.cost;
					if(next.node == v1 && d3[next.node] > nextD) {
						d3[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, true, true));
					}
					else if(d2[next.node] > nextD) {
						d2[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, false, true));
					}
				}
				else if(tmp.node1 && tmp.node2) { // v1, v2 모두 지나간 경우
					if(tmp.cost > d3[tmp.node]) continue;
					int nextD = d3[tmp.node] + next.cost;
					if(d3[next.node] > nextD) {
						d3[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, true, true));
					}
				}
				else { // 둘 다 지나가지 않은 경우
					if(tmp.cost > d[tmp.node]) continue; 
					int nextD = d[tmp.node] + next.cost;
					if(next.node == v1 && d1[next.node] > nextD) {
						d1[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, true, false));
					}
					else if(next.node == v2 && d2[next.node] > nextD) {
						d2[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, false, true));
					}
					else if(d[next.node] > nextD){
						d[next.node] = nextD;
						pQ.offer(new Edge(next.node, nextD, false, false));
					}
				}
			}
		}
		if(d3[N] == Integer.MAX_VALUE) return -1;
		else return d3[N];
	}
		
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		d = new int[N + 1];
		d1 = new int[N + 1]; // v1 을 지난 노드
		d2 = new int[N + 1]; // v2 을 지난 노드
		d3 = new int[N + 1]; // v1, v2 모두 지난 노드
		Arrays.fill(d, Integer.MAX_VALUE);
		Arrays.fill(d1, Integer.MAX_VALUE);
		Arrays.fill(d2, Integer.MAX_VALUE);
		Arrays.fill(d3, Integer.MAX_VALUE);
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0 ; i < E ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Edge(end, cost, false, false));
			list.get(end).add(new Edge(start, cost, false, false));
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(v1 ,v2, N));
		
	}
}
