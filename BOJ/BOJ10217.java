import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #10217 KCM Travel
class Ticket implements Comparable<Ticket>{
	int v; // 도착 공항
	int c; // 비용
	int d; // 소요시간
	public Ticket(int v, int c, int d) {
		this.v = v;
		this.c = c;
		this.d = d;
	}
	@Override
	public int compareTo(Ticket o) {
		if(this.d == o.d) return this.c - o.c; // 시간이 같으면 비용으로 오름차순
		else return this.d - o.d;
	}
}

public class Main {

	static ArrayList<Ticket>[] list;
	static int[][] dp; // >> 메모리 초과시 hashmap
	
	static int dijkstra(int N, int M) {
		PriorityQueue<Ticket> pQ = new PriorityQueue<>();
		pQ.offer(new Ticket(1, 0, 0));
		Arrays.fill(dp[1], 0);
		while(!pQ.isEmpty()) {
			Ticket tmp = pQ.poll();
			if(tmp.v == N) return tmp.d; // pQ가 시간 오름차순 
			if(dp[tmp.v][tmp.c] < tmp.d) continue;
			for(Ticket next : list[tmp.v]) {
				int nc = tmp.c + next.c;
				int nd = tmp.d + next.d;
				if(nc > M) continue;
				if(dp[next.v][nc] <= nd) continue;
				for(int i = nc ; i <= M ; i ++) {
					dp[next.v][i] = nd; // * 더 큰 비용은 최단거리 저장 * 
				}
				pQ.offer(new Ticket(next.v, nc, nd));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			list = new ArrayList[N + 1];
			dp = new int[N + 1][M + 1]; // dp[노드][비용] = 시간
			for(int i = 1 ; i <= N ; i ++) {
				list[i] = new ArrayList<>();
				Arrays.fill(dp[i], 100000000);
			}
			for(int i = 0 ; i < K ; i ++) { // 단방향 
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list[u].add(new Ticket(v, c, d));
			}
			int result = T.dijkstra(N, M);
			if(result == -1) sb.append("Poor KCM").append('\n');
			else sb.append(result).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
