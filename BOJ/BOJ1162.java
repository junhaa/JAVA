import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1162 도로포장

class Edge implements Comparable<Edge>{
	int node;
	long cost;
	int cnt;
	public Edge(int node, long cost, int cnt) {
		this.node = node;
		this.cost = cost;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Edge o) {
		if(this.cost < o.cost) return -1;
		else if (this.cost == o.cost) return 0;
		else return 1;
	}
}

public class Main {
	static ArrayList<Edge>[] list;
	static long[][] dp;
	
	static long dijkstra(int K, int N) {
		long answer = Long.MAX_VALUE;
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(1, 0, 0));
		Arrays.fill(dp[1], 0);
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(dp[tmp.node][tmp.cnt] < tmp.cost) continue;
			for(Edge next : list[tmp.node]) {
				long nextD = dp[tmp.node][tmp.cnt] + next.cost;
				if(dp[next.node][tmp.cnt] > nextD) {
					dp[next.node][tmp.cnt] = nextD;
					pQ.offer(new Edge(next.node, nextD, tmp.cnt));
				}
				if(tmp.cnt < K) {
					if(dp[next.node][tmp.cnt + 1] > dp[tmp.node][tmp.cnt]) {
						dp[next.node][tmp.cnt + 1] = dp[tmp.node][tmp.cnt];
						pQ.offer(new Edge(next.node, dp[next.node][tmp.cnt + 1], tmp.cnt + 1));	
					}
				}
			}
		}
		for(int i = 0 ; i <= K ; i ++) {
			answer = Math.min(answer, dp[N][i]);
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		dp = new long[N + 1][K + 1]; // [노드][포장한 도로의 수]
		for(int i = 0 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, cost, 0));
			list[end].add(new Edge(start, cost, 0)); // 양방향 간선
		}
		System.out.println(T.dijkstra(K, N));
	}
}
