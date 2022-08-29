import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #10423 전기가 부족해
class Edge implements Comparable<Edge>{
	int node, cost;
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static HashSet<Integer> hs = new HashSet<>();
	static ArrayList<Edge>[] list;
	static boolean[] ch;
	static int cnt;
	
	static int solution() {
		int answer = 0; // 가중치 합
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		for(int x : hs) {
			ch[x] = true;
			cnt --;
			for(Edge t : list[x]) {
				pQ.offer(t);
			}
		}
		while(true) {
			Edge tmp = pQ.poll();
			if(ch[tmp.node]) continue;
			ch[tmp.node] = true;
			answer += tmp.cost;
			cnt --;
			if(cnt == 0) return answer;
			for(Edge x : list[tmp.node]) {
				pQ.offer(x);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		cnt = N;
		ch = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			hs.add(Integer.parseInt(st.nextToken()));
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(hs.contains(start) && hs.contains(end)) continue;
			list[start].add(new Edge(end, cost));
			list[end].add(new Edge(start, cost));
		}
		System.out.println(T.solution());
	}
}
