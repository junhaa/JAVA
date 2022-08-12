import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1922 네트워크 연결
class Edge implements Comparable<Edge>{
	int start, end, cost;
	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int[] unf;
	static PriorityQueue<Edge> pQ = new PriorityQueue<>();
	
	static int find(int x) {
		if(unf[x] == x) return x;
		else return unf[x] = find(unf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) unf[fb] = fa;
		else unf[fa] = fb;
	}
	
	static int solution() { // Kruscal
		int answer = 0 ;
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(find(tmp.start) == find(tmp.end)) continue;
			union(tmp.start, tmp.end);
			answer += tmp.cost;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		unf = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) unf[i] = i;
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pQ.offer(new Edge(start, end, cost));
		}
		System.out.println(T.solution());
	}
}
